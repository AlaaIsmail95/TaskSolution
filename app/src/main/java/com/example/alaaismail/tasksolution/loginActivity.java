package com.example.alaaismail.tasksolution;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alaaismail.tasksolution.ModelView.facebookViewModel;
import com.example.alaaismail.tasksolution.view.facebookActivity;
import com.facebook.login.LoginManager;

public class loginActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    Button logout;

    TextView userName , email_tv;
    ImageView imageView;
    Bitmap bmp;
    private LruCache<String, Bitmap> memoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final facebookViewModel FacebookViewModel = new facebookViewModel();
        imageView = findViewById(R.id.imageView);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;

        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {

                return bitmap.getByteCount() / 1024;
            }
        };
        addBitmapToMemoryCache("profilePic",bmp);
        imageView.setImageBitmap(bmp);
        logout = findViewById(R.id.logOut);
        userName = findViewById(R.id.userName);
        email_tv = findViewById(R.id.email_tv);

        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE);

        String user_Name = sharedPref.getString("FirstName", "") + " " + sharedPref.getString("LastName", "");

        String email = sharedPref.getString("Username", "");
        userName.setText(user_Name);
        email_tv.setText(email);

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
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }
    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }
}
