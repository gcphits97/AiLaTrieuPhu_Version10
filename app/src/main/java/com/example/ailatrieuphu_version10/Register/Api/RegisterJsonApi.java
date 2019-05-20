package com.example.ailatrieuphu_version10.Register.Api;

import com.example.ailatrieuphu_version10.Login.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterJsonApi {
    @POST("user/register")
    Call<UserModel> createUser(@Body UserModel userModel);
}
