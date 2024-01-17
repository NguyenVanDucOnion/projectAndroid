package com.example.androidprojectbtl.View.Account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprojectbtl.Presenter.UserPreSenter;
import com.example.androidprojectbtl.Presenter.UserView;
import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity  extends AppCompatActivity  implements UserView, View.OnClickListener {
    private Button btndangnhap;
    private EditText editemail,editpass;
    private UserPreSenter userPreSenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        InitWidget();
        Init();
    }

    private void Init() {
        userPreSenter = new UserPreSenter(this);
        btndangnhap.setOnClickListener(this);
        findViewById(R.id.txtdangky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));

            }
        });
/*        findViewById(R.id.txtadmin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( SignInActivity.this, SignInAdminActivity.class));

            }
        });*/

    }

    private void InitWidget() {
        btndangnhap = findViewById(R.id.btndangnhap);
        editemail=findViewById(R.id.editEmail);
        editpass = findViewById(R.id.editmatkhau);
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
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void OnAuthEmail() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
        Toast.makeText(this, "Mời bạn hãy vào gmail xác thực !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this, "Sai tài khoản / Mật khẩu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPassNotSame() {
        Toast.makeText(this, "Tài khoản mật khẩu không khớp", Toast.LENGTH_SHORT).show();
    }
    /// ẤN đang nhap
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.btndangnhap){
                String email=editemail.getText().toString();
                String pass =editpass.getText().toString().trim();
                userPreSenter.HandleLoginUser(email,pass);
        }
    }
}
