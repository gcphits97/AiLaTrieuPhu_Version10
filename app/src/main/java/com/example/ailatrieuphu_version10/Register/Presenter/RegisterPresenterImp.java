package com.example.ailatrieuphu_version10.Register.Presenter;

import android.widget.EditText;
import android.widget.TextView;

public interface RegisterPresenterImp {

    void registerDataChanged(TextView txtRegisterUserName, TextView txtRegisterPassword, EditText edtRegisterUserName, EditText edtRegisterPassword, EditText edtHoTen);

    boolean isUserNameValid(String username);

    boolean isPasswordValid(String password);
}
