package com.example.ailatrieuphu_version10.login.fragment;

import android.widget.EditText;
import android.widget.TextView;

public interface LoginViewImp {
    void isLoginSuccess();
    void isLoginFailed();
    void isUserNameLoginValid(TextView txtLoginUserName, EditText edtLoginUserName);
    void isPasswordLoginValid(TextView txtLoginPassword, EditText edtLoginPassword);
}
