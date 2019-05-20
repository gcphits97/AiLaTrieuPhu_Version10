package com.example.ailatrieuphu_version10.Register.Presenter;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.Login.Model.UserModel;
import com.example.ailatrieuphu_version10.Login.Session.LoginSession;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.Register.Api.RegisterJsonApi;
import com.example.ailatrieuphu_version10.Register.Fragment.RegisterViewImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPresenterModel implements RegisterPresenterImp {
    private RegisterViewImp registerViewImp;
    private Context context;

    public RegisterPresenterModel(RegisterViewImp registerViewImp, Context context) {
        this.registerViewImp = registerViewImp;
        this.context = context;
    }

    public void registerDataChanged(TextView txtRegisterUserName, TextView txtRegisterPassword, EditText edtRegisterUserName, EditText edtRegisterPassword, EditText edtHoTen) {
        String username = edtRegisterUserName.getText().toString();
        String password = edtRegisterPassword.getText().toString();
        String hoten = edtHoTen.getText().toString();
        if (isUserNameValid(username)) {
            registerViewImp.isUserNameRegisterValid(txtRegisterUserName, edtRegisterUserName);
        } else if (isPasswordValid(password)) {
            registerViewImp.isPasswordRegisterValid(txtRegisterPassword, edtRegisterPassword);
        } else {
//            registerViewImp.isRegisterSuccess();
            UserModel userModel = new UserModel(username, password, hoten);

            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.url))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            RegisterJsonApi registerJsonApi = retrofit.create(RegisterJsonApi.class);

            Call<UserModel> userModelCall = registerJsonApi.createUser(userModel);

            userModelCall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful()) {
                        UserModel createUser = response.body();
                        if (createUser != null) {
                            LoginSession.setLoggedIn(context, true, createUser.getUsername(), createUser.getFullname());
                            registerViewImp.isRegisterSuccess();
                        }
                    } else {
                        Toast.makeText(context, context.getText(R.string.register_failed), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(context, context.getText(R.string.connection_register_failed), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    //kiem tra username co null hay khong
    public boolean isUserNameValid(String username) {
        if (username.trim().isEmpty()) {
            return true;
        } else if (username.contains("@")) {
            return !Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return username.trim().isEmpty();
        }
    }
    //tra ve 1 chuoi khac null va co do dai lon hon 5
    public boolean isPasswordValid(String password) {
        return password == null || password.trim().length() <= 5;
    }
}
