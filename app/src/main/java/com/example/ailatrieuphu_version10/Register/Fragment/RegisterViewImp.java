package com.example.ailatrieuphu_version10.Register.Fragment;

import android.widget.EditText;
import android.widget.TextView;

public interface RegisterViewImp {
    void isRegisterSuccess();
    void isUserNameRegisterValid(TextView txtRegisterUserName, EditText edtRegisterUserName);
    void isPasswordRegisterValid(TextView txtRegisterPassword, EditText edtRegisterPassword);
}
