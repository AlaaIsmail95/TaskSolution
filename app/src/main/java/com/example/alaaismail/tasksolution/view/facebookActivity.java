package com.example.alaaismail.tasksolution.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alaaismail.tasksolution.R;
import com.example.alaaismail.tasksolution.loginActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.example.alaaismail.tasksolution.ModelView.facebookViewModel;

import org.json.JSONObject;

import java.util.zip.Inflater;


public class facebookActivity extends AppCompatActivity {

    LoginButton bu_fbLogin;
    TextView tv_username, name;
    CheckBox remmberme;
    CallbackManager callbackManager;
    String username,firstName,Lastname;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        final facebookViewModel FacebookViewModel = new facebookViewModel();

        bu_fbLogin = findViewById(R.id.bu_loginwithFB);

        tv_username = findViewById(R.id.UserName);
        name = findViewById(R.id.FullName);
        remmberme = findViewById(R.id.cb_remmberMe);


        callbackManager = CallbackManager.Factory.create();

        bu_fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Profile profile = Profile.getCurrentProfile();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    username = object.getString("email");
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                );
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
               // username = profile.getName();
                firstName = profile.getFirstName();
                Lastname = profile.getLastName();

                tv_username.setText(username);
                name.setText(firstName+ " "+Lastname);
                try {
                    if (remmberme.isChecked()) {

                        FacebookViewModel.RemmberLogin(facebookActivity.this,username, firstName, Lastname);

                    } else {
                        FacebookViewModel.forgetLogin(facebookActivity.this,username, firstName, Lastname);

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(facebookActivity.this, loginActivity.class);
                startActivity(intent);
                facebookActivity.this.finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();

            }
        });







    }
}
