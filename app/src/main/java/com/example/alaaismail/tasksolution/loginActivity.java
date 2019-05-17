package com.example.alaaismail.tasksolution;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alaaismail.tasksolution.ModelView.facebookViewModel;
import com.example.alaaismail.tasksolution.view.facebookActivity;
import com.facebook.login.LoginManager;

public class loginActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    Button logout;

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final facebookViewModel FacebookViewModel = new facebookViewModel();

        logout = findViewById(R.id.logOut);
        userName = findViewById(R.id.userName);
        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE);

        String user_Name = sharedPref.getString("FirstName", "") + " " + sharedPref.getString("LastName", "");

        userName.setText(user_Name);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                FacebookViewModel.logout(loginActivity.this);
                Intent intent = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);
                loginActivity.this.finish();

            }
        });

    }
}
