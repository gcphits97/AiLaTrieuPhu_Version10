package com.example.ailatrieuphu_version10.highScore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.retrofit.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.high_score_activity);

        Call<List<HighScoreModel>> call = APIClient.getPlayerList().getPlayerList();

        call.enqueue(new Callback<List<HighScoreModel>>() {
            @Override
            public void onResponse(Call<List<HighScoreModel>> call, Response<List<HighScoreModel>> response) {
                if (response.body() != null) {
                    Log.d("ahihi123", "onResponse: "+ response.body());
                } else {
                    Log.d("ahhi123", "onResponse: chưa lấy được dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<List<HighScoreModel>> call, Throwable t) {

            }
        });
    }
}
