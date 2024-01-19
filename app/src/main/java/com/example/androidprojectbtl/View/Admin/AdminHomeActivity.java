package com.example.androidprojectbtl.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.Account.SignInActivity;
import com.example.androidprojectbtl.View.Admin.Product.ListProductActivity;
import com.example.androidprojectbtl.View.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {
    CardView btnProductManage, cSignOut;
    private FirebaseAuth firebaseAuth;

    ImageView ivhoadon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnProductManage = findViewById(R.id.btnProductManage);
        cSignOut = findViewById(R.id.cSignOut);
        ivhoadon = findViewById(R.id.ivhoadon);
        initEvents();
    }

    public void initEvents() {
        btnProductManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });

        cSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();startActivity(new Intent(AdminHomeActivity.this, SignInAdminActivity.class));finish();
            }
        });

        ivhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, BillADMiNActivity.class));
            }
        });
    }
}