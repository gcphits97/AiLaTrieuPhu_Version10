package com.example.ailatrieuphu_version10.login.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.login.presenter.LoginPresenterModel;
import com.example.ailatrieuphu_version10.login.session.LoginSession;
import com.example.ailatrieuphu_version10.player.view.PlayerActivity;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.register.fragment.RegisterFragment;

import java.util.Objects;

public class LoginFragment extends Fragment implements View.OnClickListener, LoginViewImp {
    EditText edtTenDangNhap, edtMatKhau;
    Button btnDangNhap, btnHuy, btnChoiNgay, btnDiemCao, btnThongTin, btnDangNhapMain, btnDangXuat, btnChoi;
    TextView txtDangKy, txtLuuYTenDangNhapFormDangNhap, txtLuuYMatKhauFormDangNhap, txtPasswordRetrieval;
    private FrameLayout frameBackgroundMain;
    private LinearLayout khungDangNhap;
    private LoginPresenterModel loginPresenterModel;
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = LayoutInflater.from(getContext()).inflate(R.layout.login_fragment, null, false);
        edtTenDangNhap = myView.findViewById(R.id.edtTenDangNhap);
        edtMatKhau = myView.findViewById(R.id.edtMatKhau);
        btnDangNhap = myView.findViewById(R.id.btnDangNhap);
        btnHuy = myView.findViewById(R.id.btnHuy);
        txtDangKy = myView.findViewById(R.id.txtDangKy);
        txtPasswordRetrieval = myView.findViewById(R.id.txtPasswordRetrieval);
        txtLuuYTenDangNhapFormDangNhap = myView.findViewById(R.id.txtLuuYTenDangNhapFormDangNhap);
        txtLuuYMatKhauFormDangNhap = myView.findViewById(R.id.txtLuuYMatKhauFormDangNhap);

        frameBackgroundMain = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackgroundMain);
        btnChoiNgay = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoiNgay);
        btnDiemCao = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDiemCao);
        btnThongTin = Objects.requireNonNull(getActivity()).findViewById(R.id.btnThongTin);
        btnDangNhapMain = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangNhap);
        khungDangNhap = Objects.requireNonNull(getActivity()).findViewById(R.id.khungDangNhap);
        btnDangXuat = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangXuat);
        btnChoi = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoi);

        loginPresenterModel = new LoginPresenterModel(this, getContext());

        btnDangNhap.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        txtDangKy.setOnClickListener(this);
        txtPasswordRetrieval.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhap:
                loginPresenterModel.loginDataChanged(txtLuuYTenDangNhapFormDangNhap, txtLuuYMatKhauFormDangNhap, edtTenDangNhap, edtMatKhau);
                break;
            case R.id.btnHuy:
                frameBackgroundMain.setVisibility(View.GONE);
                btnChoiNgay.setEnabled(true);
                btnDiemCao.setEnabled(true);
                btnThongTin.setEnabled(true);
                btnDangNhapMain.setEnabled(true);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(this).commit();
                break;
            case R.id.txtDangKy:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParentMain, new RegisterFragment()).commit();
                break;
            case R.id.txtPasswordRetrieval:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParentMain, new PasswordRetrievalFragment()).commit();
                break;
        }
    }

    //chuyen trang khi login hoac register thanh cong
    @Override
    public void isLoginSuccess() {
        Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.login_success)+", "+ LoginSession.getFullName(getContext()), Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), PlayerActivity.class)); //chuyen trang
        khungDangNhap.setVisibility(View.GONE);
        btnChoi.setVisibility(View.VISIBLE);
        btnDangXuat.setVisibility(View.VISIBLE);
        Objects.requireNonNull(getActivity()).finish();         //destroy activity
    }
    //ket thuc chuyen trang khi login hoac register thanh cong
    @Override
    public void isLoginFailed() {
        Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.login_failed), Toast.LENGTH_SHORT).show();
    }
    //kiem tra username va password form dang nhap
    @Override
    public void isUserNameLoginValid(final TextView txtLoginUserName, EditText edtLoginUserName) {
        txtLoginUserName.setText(R.string.invalid_login_username);
        edtLoginUserName.addTextChangedListener(new TextWatcher() { //lang nghe text thay doi trong o edittext
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtLoginUserName.setText(""); //neu text trong edittext thay doi thi set text bang rong
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void isPasswordLoginValid(final TextView txtLoginPassword, EditText edtLoginPassword) {
        txtLoginPassword.setText(R.string.invalid_login_password);
        edtLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtLoginPassword.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //ket thuc kiem tra username va password form dang nhap
}
