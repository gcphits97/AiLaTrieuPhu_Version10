package com.example.ailatrieuphu_version10.Register.Fragment;

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

import com.example.ailatrieuphu_version10.Login.Fragment.LoginFragment;
import com.example.ailatrieuphu_version10.Register.Presenter.RegisterPresenterModel;
import com.example.ailatrieuphu_version10.Player.View.PlayerActivity;
import com.example.ailatrieuphu_version10.R;

import java.util.Objects;

public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterViewImp {
    EditText edtTenDangNhap, edtMatKhau, edtHoTen;
    Button btnDangKy, btnHuy, btnChoiNgay, btnDiemCao, btnThongTin, btnDangNhap, btnDangXuat, btnChoi;
    TextView txtDangNhap, txtLuuYTenDangNhapFormDangKy, txtLuuYMatKhauFormDangKy;
    private FrameLayout frameBackgroundMain;
    private LinearLayout khungDangNhap;
    private RegisterPresenterModel registerPresenterModel;
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = LayoutInflater.from(getContext()).inflate(R.layout.register_fragment, null, false);
        edtTenDangNhap = myView.findViewById(R.id.edtTenDangNhap);
        edtMatKhau = myView.findViewById(R.id.edtMatKhau);
        edtHoTen = myView.findViewById(R.id.edtHoTen);
        btnDangKy = myView.findViewById(R.id.btnDangKy);
        btnHuy = myView.findViewById(R.id.btnHuy);
        txtDangNhap = myView.findViewById(R.id.txtDangNhap);
        txtLuuYTenDangNhapFormDangKy = myView.findViewById(R.id.txtLuuYTenDangNhapFormDangKy);
        txtLuuYMatKhauFormDangKy = myView.findViewById(R.id.txtLuuYMatKhauFormDangKy);

        frameBackgroundMain = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackgroundMain);
        btnChoiNgay = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoiNgay);
        btnDiemCao = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDiemCao);
        btnThongTin = Objects.requireNonNull(getActivity()).findViewById(R.id.btnThongTin);
        btnDangNhap = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangNhap);
        khungDangNhap = Objects.requireNonNull(getActivity()).findViewById(R.id.khungDangNhap);
        btnDangXuat = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangXuat);
        btnChoi = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoi);

        registerPresenterModel = new RegisterPresenterModel(this, getContext());

        btnDangKy.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        txtDangNhap.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                registerPresenterModel.registerDataChanged(txtLuuYTenDangNhapFormDangKy, txtLuuYMatKhauFormDangKy, edtTenDangNhap, edtMatKhau, edtHoTen);
                break;
            case R.id.btnHuy:
                frameBackgroundMain.setVisibility(View.GONE);
                btnChoiNgay.setEnabled(true);
                btnDiemCao.setEnabled(true);
                btnThongTin.setEnabled(true);
                btnDangNhap.setEnabled(true);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(this).commit();
                break;
            case R.id.txtDangNhap:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParentMain, new LoginFragment()).commit();
                break;
        }
    }

    @Override
    public void isRegisterSuccess() {
        Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.register_success), Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), PlayerActivity.class)); //chuyen trang
        khungDangNhap.setVisibility(View.GONE);
        btnChoi.setVisibility(View.VISIBLE);
        btnDangXuat.setVisibility(View.VISIBLE);
        Objects.requireNonNull(getActivity()).finish();         //destroy activity
    }

    //kiem tra username va password form dang ky
    @Override
    public void isUserNameRegisterValid(final TextView txtRegisterUserName, EditText edtRegisterUserName) {
        txtRegisterUserName.setText(R.string.invalid_register_username);
        edtRegisterUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtRegisterUserName.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void isPasswordRegisterValid(final TextView txtRegisterPassword, EditText edtRegisterPassword) {
        txtRegisterPassword.setText(R.string.invalid_register_password);
        edtRegisterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtRegisterPassword.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //ket thuc kiem tra username va password form dang ky
}
