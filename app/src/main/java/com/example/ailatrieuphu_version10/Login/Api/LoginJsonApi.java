package com.example.ailatrieuphu_version10.Login.Api;

import com.example.ailatrieuphu_version10.Login.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginJsonApi {
    @POST("user/login")
    Call<UserModel> checkUser(@Body UserModel userModel);
}
