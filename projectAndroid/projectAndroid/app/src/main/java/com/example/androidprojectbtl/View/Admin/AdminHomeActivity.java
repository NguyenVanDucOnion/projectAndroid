package com.example.androidprojectbtl.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.Admin.Product.ListProductActivity;

public class AdminHomeActivity extends AppCompatActivity {
    CardView btnProductManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnProductManage = findViewById(R.id.btnProductManage);
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
    }
}