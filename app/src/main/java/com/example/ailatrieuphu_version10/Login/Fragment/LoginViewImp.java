package com.example.ailatrieuphu_version10.Login.Fragment;

import android.widget.EditText;
import android.widget.TextView;

public interface LoginViewImp {
    void isLoginSuccess();
    void isLoginFalse();
    void isUserNameLoginValid(TextView txtLoginUserName, EditText edtLoginUserName);
    void isPasswordLoginValid(TextView txtLoginPassword, EditText edtLoginPassword);
}
