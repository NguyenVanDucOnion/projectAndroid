package com.example.androidprojectbtl.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.Account.SignInActivity;
import com.example.androidprojectbtl.View.Admin.AdminHomeActivity;
import com.example.androidprojectbtl.View.Admin.Product.ListProductActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Fragment fragment;
    private FirebaseAuth firebaseAuth;
    private EditText editsearch;
    private TextView tvusername, tvemail;
    private CircleImageView imaProfile;

    private Button btnGoToProducts;

    public static CountDownTimer countDownTimer;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitWidget();
        Init();
        setProfile();

    }

    private void setProfile() {
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnGoToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidget() {
        navigationView = findViewById(R.id.navigationview);
        View headerLayout = navigationView.getHeaderView(0);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        editsearch = findViewById(R.id.editSearch);
        tvusername = headerLayout.findViewById(R.id.tvusername);
        tvemail = headerLayout.findViewById(R.id.tvemail);
        imaProfile = headerLayout.findViewById(R.id.profile_image);
        btnGoToProducts = findViewById(R.id.btn_go_to_product);
    }
}