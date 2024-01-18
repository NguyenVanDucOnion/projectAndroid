package com.example.androidprojectbtl.View.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidprojectbtl.Presenter.UserPreSenter;
import com.example.androidprojectbtl.Presenter.UserView;
import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements UserView , View.OnClickListener {
    private Button btndangky;

    private TextView tvToSignIn;
    private EditText editemail,editpass,editpass_repeat;
    private UserPreSenter userPreSenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitWidget();
        Init();

        tvToSignIn = findViewById(R.id.tvToSignIn);
        tvToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }

    private void Init() {
        userPreSenter = new UserPreSenter(this);
        btndangky.setOnClickListener(this);
    }

    private void InitWidget() {
        btndangky =findViewById(R.id.btndangky);
        editemail = findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
        editpass_repeat = findViewById((R.id.editmatkhau_repeat));
    }


    @Override
    public void OnLengthEmail() {
        Toast.makeText(this, "Email không để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnValidEmail() {
        Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPass() {
        Toast.makeText(this, "Mật khẩu không để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSucess() {
        startActivity(new Intent(this, HomeActivity.class));
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void OnAuthEmail() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
        Toast.makeText(this, "Hãy vào gamil để xác thực tài khoản của bạn !", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this, "Tài khoản đã được đăng ký !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPassNotSame() {
        Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btndangky) {
            String email = editemail.getText().toString();
            String pass = editpass.getText().toString().trim();
            String repass = editpass_repeat.getText().toString().trim();
            userPreSenter.HandleResgist(email, pass, repass);
        }
    }
}