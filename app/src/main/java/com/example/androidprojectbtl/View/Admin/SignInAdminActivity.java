package com.example.androidprojectbtl.View.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprojectbtl.Presenter.UserPreSenter;
import com.example.androidprojectbtl.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInAdminActivity extends AppCompatActivity  implements  View.OnClickListener {
    private Button btndangnhap;
    private EditText editemail, editpass;
    private UserPreSenter userPreSenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);
        InitWidget();
        Init();
    }

    private void Init() {

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInAdminActivity.this, HomeAdminActivity.class));
                finish();
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void InitWidget() {
        btndangnhap = findViewById(R.id.btndangnhap);
        editemail = findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btndangnhap:
                String username = editemail.getText().toString();
                String pass = editpass.getText().toString().trim();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Admin").whereEqualTo("username", username).whereEqualTo("pass", pass).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            startActivity(new Intent(SignInAdminActivity.this, HomeAdminActivity.class));
                            finish();

                    }
                });
                    }
                }


        }