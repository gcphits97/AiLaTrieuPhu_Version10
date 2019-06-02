package com.example.ailatrieuphu_version10.retrofit;

import com.example.ailatrieuphu_version10.login.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterJsonApi {
    @POST("user/register")
    Call<UserModel> createUser(@Body UserModel userModel);
}
