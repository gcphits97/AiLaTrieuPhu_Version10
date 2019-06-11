package com.example.ailatrieuphu_version10.retrofit;

import com.example.ailatrieuphu_version10.highScore.HighScoreModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HighScoreJsonApi {
    @Headers("Content-type: application/json")
    @GET("highscore/get")
    Call<List<HighScoreModel>> getPlayerList();

    @POST("highscore/create-update")
    Call<HighScoreModel> createOrUpdateHighScore(@Body HighScoreModel highScoreModel);
}
