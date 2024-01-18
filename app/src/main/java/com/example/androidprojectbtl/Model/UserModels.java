package com.example.androidprojectbtl.Model;

import androidx.annotation.NonNull;

import com.example.androidprojectbtl.Presenter.IUSER;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserModels {
    private FirebaseAuth firebaseAuth;
    private String valid_Email ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private  String email;
    private  String pass;
    private IUSER callback;

    public UserModels(IUSER callback) {
        this.callback =callback;
        firebaseAuth =FirebaseAuth.getInstance();
    }

    public void HandleLoginUser(String email, String pass) {
        if(email.length() > 0) {
            if(email.matches(valid_Email)) {
                if(pass.length()>0) {
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    callback.OnSucess();
                                } else {
                                    callback.OnAuthEmail();
                                }
                            } else {
                                callback.OnFail();
                            }
                        }
                    });
                } else {
                    callback.OnPass();
                }
            } else {
                callback.OnValidEmail();
            }
        } else {
            callback.OnLengthEmail();
        }
    }
    public void HandleRegistUser(String email, String pass, String repass) {
        if(email.length()>0){

            if(email.matches(valid_Email)){
                if(pass.length()>0){
                    if(pass.equals(repass)){
                        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    callback.OnAuthEmail();
                                }else{
                                    callback.OnFail();
                                }

                            }
                        });
                    }else{
                        callback.OnPassNotSame();
                    }

                }else{
                    callback.OnPass();
                }
            }else{
                callback.OnValidEmail();
            }

        }else{
            callback.OnLengthEmail();
        }
    }

}

