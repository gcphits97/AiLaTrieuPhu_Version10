package com.example.ailatrieuphu_version10.Player.Presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.Player.Api.PlayerJsonApi;
import com.example.ailatrieuphu_version10.Player.Model.QuestionModel;
import com.example.ailatrieuphu_version10.Player.View.PlayerViewImp;
import com.example.ailatrieuphu_version10.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerPresenterModel implements PlayerPresenterImp {
    private Context context;
    private PlayerViewImp playerViewImp;

    public PlayerPresenterModel(Context context, PlayerViewImp playerViewImp) {
        this.context = context;
        this.playerViewImp = playerViewImp;
    }

    @Override
    public void setEventClickAnswer(final TextView txtQuestion, final TextView txtAnswerA, final TextView txtAnswerB, final TextView txtAnswerC, final TextView txtAnswerD) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PlayerJsonApi playerJsonApi = retrofit.create(PlayerJsonApi.class);

        Call<List<QuestionModel>> call = playerJsonApi.getQuestionList();
        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                if (response.isSuccessful()) {
                    List<QuestionModel> questionList = response.body();
                    showQuestion(questionList, txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD);
                } else {
                    Toast.makeText(context, context.getText(R.string.error_get_question)+": "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                Toast.makeText(context, context.getText(R.string.error_get_question)+": "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        resetTime();
//
//        if (listSize > 0) {
//            Random rd = new Random();
//            x = rd.nextInt(listSize);
//            txtQuestion.setText(test.get(x).getQuestion());
//            txtAnswerA.setText("A: " + test.get(x).getAnswera());
//            txtAnswerB.setText("B: " + test.get(x).getAnswerb());
//            txtAnswerC.setText("C: " + test.get(x).getAnswerc());
//            txtAnswerD.setText("D: " + test.get(x).getAnswerd());
//
//            txtAnswerA.setOnClickListener(this);
//            txtAnswerB.setOnClickListener(this);
//            txtAnswerC.setOnClickListener(this);
//            txtAnswerD.setOnClickListener(this);
//
//            txtAnswerB.setEnabled(true);
//            txtAnswerC.setEnabled(true);
//            txtAnswerD.setEnabled(true);
//            txtAnswerA.setEnabled(true);
//            txtAnswerA.setBackgroundResource(R.drawable.question_background);
//            txtAnswerB.setBackgroundResource(R.drawable.question_background);
//            txtAnswerC.setBackgroundResource(R.drawable.question_background);
//            txtAnswerD.setBackgroundResource(R.drawable.question_background);
//        } else {
//            Toast.makeText(this, "Het roi nhe", Toast.LENGTH_SHORT).show();
//        }
    }

    @SuppressLint("SetTextI18n")
    private void showQuestion(List<QuestionModel> questionList, TextView txtQuestion, TextView txtAnswerA, TextView txtAnswerB, TextView txtAnswerC, TextView txtAnswerD) {
        for (int i = 0; i < questionList.size(); ) {
            txtQuestion.setText(questionList.get(i).getQuestion());
            txtAnswerA.setText("A: " + questionList.get(i).getAnswera());
            txtAnswerB.setText("B: " + questionList.get(i).getAnswerb());
            txtAnswerC.setText("C: " + questionList.get(i).getAnswerc());
            txtAnswerD.setText("D: " + questionList.get(i).getAnswerd());
        }
    }

    @Override
    public void setEventCheckAnswer() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                switch (test.get(x).getRightanswer()) {
//                    case 1:
//                        txtAnswerA.setBackgroundResource(R.drawable.anwser_background);
//                        break;
//                    case 2:
//                        txtAnswerB.setBackgroundResource(R.drawable.anwser_background);
//                        break;
//                    case 3:
//                        txtAnswerC.setBackgroundResource(R.drawable.anwser_background);
//                        break;
//                    case 4:
//                        txtAnswerD.setBackgroundResource(R.drawable.anwser_background);
//                        break;
//                }
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (test.get(x).getRightanswer() == result) {
//                            ++reward;
//                            textViews[reward] = findViewById(idArray[reward]);
//                            textViews[reward].setBackgroundResource(R.drawable.question_choice);
//                            if (reward >= 1) {
//                                textViews[reward - 1].setBackgroundResource(R.drawable.question_background);
//                            }
//                            test.remove(x);
//                            testSuKien();
//                        } else {
//                            frameBackground.setVisibility(View.VISIBLE);
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, new EndGameFragment()).commit();
//                        }
//                    }
//                }, GAME_TIME);
//            }
//        }, GAME_TIME);
    }
}
