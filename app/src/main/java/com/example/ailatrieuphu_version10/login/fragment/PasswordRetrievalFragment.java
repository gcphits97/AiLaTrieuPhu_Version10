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

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.login.model.UserModel;
import com.example.ailatrieuphu_version10.login.session.LoginSession;
import com.example.ailatrieuphu_version10.player.view.PlayerActivity;
import com.example.ailatrieuphu_version10.retrofit.APIClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordRetrievalFragment extends Fragment implements View.OnClickListener {
    EditText edtTenDangNhap, edtMatKhau, edtNhapLaiMatKhau;
    Button btnConfirm, btnHuy, btnChoiNgay, btnDiemCao, btnThongTin, btnDangNhap, btnDangXuat, btnChoi;
    TextView txtDangNhap, txtLuuYTenDangNhapFormPasswordRetrieval, txtLuuYMatKhauFormPasswordRetrieval, txtLuuYPasswordRetrieval;
    private FrameLayout frameBackgroundMain;
    private LinearLayout khungDangNhap;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View myView = LayoutInflater.from(getContext()).inflate(R.layout.password_retrieval_fragment, null, false);
        edtTenDangNhap = myView.findViewById(R.id.edtTenDangNhap);
        edtMatKhau = myView.findViewById(R.id.edtMatKhau);
        edtNhapLaiMatKhau = myView.findViewById(R.id.edtNhapLaiMatKhau);
        btnConfirm = myView.findViewById(R.id.btnConfirm);
        btnHuy = myView.findViewById(R.id.btnHuy);
        txtDangNhap = myView.findViewById(R.id.txtDangNhap);
        txtLuuYTenDangNhapFormPasswordRetrieval = myView.findViewById(R.id.txtLuuYTenDangNhapFormPasswordRetrieval);
        txtLuuYMatKhauFormPasswordRetrieval = myView.findViewById(R.id.txtLuuYMatKhauFormPasswordRetrieval);
        txtLuuYPasswordRetrieval = myView.findViewById(R.id.txtLuuYPasswordRetrieval);

        frameBackgroundMain = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackgroundMain);
        btnChoiNgay = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoiNgay);
        btnDiemCao = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDiemCao);
        btnThongTin = Objects.requireNonNull(getActivity()).findViewById(R.id.btnThongTin);
        btnDangNhap = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangNhap);
        khungDangNhap = Objects.requireNonNull(getActivity()).findViewById(R.id.khungDangNhap);
        btnDangXuat = Objects.requireNonNull(getActivity()).findViewById(R.id.btnDangXuat);
        btnChoi = Objects.requireNonNull(getActivity()).findViewById(R.id.btnChoi);


        btnConfirm.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        txtDangNhap.setOnClickListener(this);

        return myView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                passwordRetrievalChangedData();
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

    @SuppressLint("SetTextI18n")
    public void passwordRetrievalChangedData() {
        if (isPasswordValid(edtMatKhau.getText().toString())) {
            edtMatKhauTextChanged();
        } else {
            if (edtMatKhau.getText().toString().equals(edtNhapLaiMatKhau.getText().toString())) {
                UserModel userModel = new UserModel(edtTenDangNhap.getText().toString(), edtMatKhau.getText().toString());
                Call<UserModel> call = APIClient.passwordRetrieval().passwordRetrieval(userModel);

                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            UserModel passwordRetrieval = response.body();
                            if (passwordRetrieval != null) {
                                LoginSession.setLoggedIn(getContext(), true, passwordRetrieval.getId(), passwordRetrieval.getUsername(), passwordRetrieval.getFullname());
                                Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.password_retrieval_success), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getActivity(), PlayerActivity.class)); //chuyen trang
                                khungDangNhap.setVisibility(View.GONE);
                                btnChoi.setVisibility(View.VISIBLE);
                                btnDangXuat.setVisibility(View.VISIBLE);
                                Objects.requireNonNull(getActivity()).finish();         //destroy activity
                            }
                        } else {
                            txtLuuYTenDangNhapFormPasswordRetrieval.setText(R.string.password_retrieval_fail);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.internet_connection_error), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                txtLuuYPasswordRetrieval.setText(R.string.error_password_retrieval);
                edtNhapLaiMatKhau.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        txtLuuYPasswordRetrieval.setText("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }
    }

    private void edtMatKhauTextChanged() {
        txtLuuYMatKhauFormPasswordRetrieval.setText(R.string.invalid_register_password);
        edtMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtLuuYMatKhauFormPasswordRetrieval.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //tra ve 1 chuoi khac null va co do dai lon hon 5
    public boolean isPasswordValid(String password) {
        return password == null || password.trim().length() <= 5;
    }
}
