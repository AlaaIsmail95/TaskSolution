package com.example.alaaismail.tasksolution.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
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

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.zip.Inflater;


public class facebookActivity extends AppCompatActivity {

    LoginButton bu_fbLogin;
    TextView tv_username, name;
    CheckBox remmberme;
    CallbackManager callbackManager;
    String username,firstName,Lastname;
    Bitmap bitmap;


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
        bu_fbLogin.setReadPermissions(Arrays.asList("email"));

        bu_fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {


                Profile profile = Profile.getCurrentProfile();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                    StrictMode.setThreadPolicy(policy);
                                    username = object.getString("email");
                                        if (remmberme.isChecked()) {

                                            FacebookViewModel.RemmberLogin(facebookActivity.this,username, firstName, Lastname);

                                        } else {
                                            FacebookViewModel.forgetLogin(facebookActivity.this,username, firstName, Lastname);

                                        }
                                    URL imageURL = new URL("https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?type=large");
                                    bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                                    byte[] byteArray = bStream.toByteArray();

                                    Intent intent = new Intent(facebookActivity.this, loginActivity.class);
                                    intent.putExtra("image",byteArray);
                                    startActivity(intent);
                                    facebookActivity.this.finish();
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
