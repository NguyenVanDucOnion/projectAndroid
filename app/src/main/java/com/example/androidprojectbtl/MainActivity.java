package com.example.androidprojectbtl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.androidprojectbtl.View.Account.SignInActivity;
import com.example.androidprojectbtl.View.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseAuth.getCurrentUser() != null) {
                    if(firebaseAuth.getCurrentUser().getEmail().length() > 0) {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                } else {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                }
                finish();
            }
        }, 3500);
    }
}