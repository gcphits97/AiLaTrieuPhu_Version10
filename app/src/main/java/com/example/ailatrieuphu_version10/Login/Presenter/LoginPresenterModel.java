package com.example.ailatrieuphu_version10.Login.Presenter;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.Login.Api.LoginJsonApi;
import com.example.ailatrieuphu_version10.Login.Fragment.LoginViewImp;
import com.example.ailatrieuphu_version10.Login.Model.UserModel;
import com.example.ailatrieuphu_version10.Login.Session.LoginSession;
import com.example.ailatrieuphu_version10.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenterModel implements LoginPresenterImp {
    private LoginViewImp loginViewImp;
    private Context context;

    public LoginPresenterModel(LoginViewImp loginViewImp, Context context) {
        this.loginViewImp = loginViewImp;
        this.context = context;
    }

    public void loginDataChanged(final TextView txtLoginUserName, TextView txtLoginPassword, final EditText edtLoginUserName, final EditText edtLoginPassword) {
        final String username = edtLoginUserName.getText().toString();
        final String password = edtLoginPassword.getText().toString();
        if (isUserNameValid(username)) {
            loginViewImp.isUserNameLoginValid(txtLoginUserName, edtLoginUserName);
        } else if (isPasswordValid(password)) {
            loginViewImp.isPasswordLoginValid(txtLoginPassword, edtLoginPassword);
        } else {
            UserModel userModel = new UserModel(username, password);

            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.url))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            LoginJsonApi loginJsonApi = retrofit.create(LoginJsonApi.class);

            Call<UserModel> userModelCall = loginJsonApi.checkUser(userModel);
            userModelCall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (!response.isSuccessful()) {
                        loginViewImp.isLoginFalse();
                    } else {
                        UserModel postResponse = response.body();
                        if (postResponse != null) {
                            LoginSession.setLoggedIn(context, true, postResponse.getUsername(), postResponse.getFullname());
                            loginViewImp.isLoginSuccess();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(context, context.getText(R.string.connection_failed), Toast.LENGTH_LONG).show();
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
