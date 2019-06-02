package com.example.ailatrieuphu_version10.login.presenter;

import android.widget.EditText;
import android.widget.TextView;

public interface LoginPresenterImp {
    void loginDataChanged(TextView txtLoginUserName, TextView txtLoginPassword, EditText edtLoginUserName, EditText edtLoginPassword);

    boolean isUserNameValid(String username);

    boolean isPasswordValid(String password);


}
