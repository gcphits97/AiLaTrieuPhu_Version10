package com.example.ailatrieuphu_version10.retrofit;

import com.example.ailatrieuphu_version10.login.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginJsonApi {
    @POST("user/login")
    Call<UserModel> checkUser(@Body UserModel userModel);

    @POST("user/passwordRetrieval")
    Call<UserModel> passwordRetrieval(@Body UserModel userModel);
}
