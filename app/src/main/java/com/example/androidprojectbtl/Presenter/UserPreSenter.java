package com.example.androidprojectbtl.Presenter;

import com.example.androidprojectbtl.Model.UserModels;

public class UserPreSenter implements IUSER{
    private UserModels userModel;
    private  UserView callback;

        public UserPreSenter(UserView callback) {
            this.callback =callback;
            userModel = new UserModels(this);
    }

    public void HandleLoginUser(String email, String pass) {
        userModel.HandleLoginUser(email,pass);
    }

    public void HandleResgist(String email, String pass, String repass) {
        userModel.HandleRegistUser(email,pass,repass);
    }

    @Override
    public void OnLengthEmail() {
            callback.OnLengthEmail();
    }

    @Override
    public void OnValidEmail() {
            callback.OnValidEmail();
    }

    @Override
    public void OnPass() {
        callback.OnPass();
    }

    @Override
    public void OnSucess() {
        callback.OnSucess();
    }

    @Override
    public void OnAuthEmail() {
        callback.OnAuthEmail();
    }

    @Override
    public void OnFail() {
        callback.OnFail();
    }

    @Override
    public void OnPassNotSame() {
        callback.OnPassNotSame();
    }


}
