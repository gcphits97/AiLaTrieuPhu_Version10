package com.example.ailatrieuphu_version10.Player.Api;

import com.example.ailatrieuphu_version10.Player.Model.QuestionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PlayerJsonApi {
    @Headers("Content-type: application/json")
    @GET("question/get")
    Call<List<QuestionModel>> getQuestionList();
}
