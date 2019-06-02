package com.example.ailatrieuphu_version10.player.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.player.model.QuestionModel;
import com.example.ailatrieuphu_version10.player.view.PlayerViewImp;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.retrofit.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerPresenterModel implements PlayerPresenterImp {
    private Context context;
    private PlayerViewImp playerViewImp;

    public PlayerPresenterModel(Context context, PlayerViewImp playerViewImp) {
        this.context = context;
        this.playerViewImp = playerViewImp;
    }

    @Override
    public void getQuestionList() {
        Call<List<QuestionModel>> call = APIClient.getQuestion().getQuestionList();
        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        playerViewImp.showQuestion(response.body());
                    } else {
                        Toast.makeText(context, context.getText(R.string.error_get_question), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, context.getText(R.string.error)+": "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                Toast.makeText(context, context.getText(R.string.error)+": "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
