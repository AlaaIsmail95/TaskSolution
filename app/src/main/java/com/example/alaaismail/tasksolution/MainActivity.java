package com.example.alaaismail.tasksolution;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.alaaismail.tasksolution.view.*;


public class MainActivity extends AppCompatActivity {

    Button facebook_task , db_task , search_task , phone_task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facebook_task = findViewById(R.id.facebook_task);
        db_task = findViewById(R.id.db_task);
        search_task = findViewById(R.id.search_bu);
        phone_task = findViewById(R.id.contact_task);

        facebook_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref;
                sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                boolean login_check = sharedPref.getBoolean("is_login", false);

                if (login_check == true) {
                    Intent SignedIn = new Intent(MainActivity.this, loginActivity.class);

                    startActivity(SignedIn);


                } else {
                    Intent ToFacebook = new Intent(MainActivity.this, facebookActivity.class);
                    startActivity(ToFacebook);
                }
            }
        });

        db_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToDataBaseTask = new Intent(MainActivity.this, databaseActivity.class);
                startActivity(ToDataBaseTask);
            }
        });
        search_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToSearchTask = new Intent(MainActivity.this, searchActivity.class);
                startActivity(ToSearchTask);
            }
        });
        phone_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToContacts_task = new Intent(MainActivity.this, contactsActivity.class);
                startActivity(ToContacts_task);
            }
        });

    }
}
