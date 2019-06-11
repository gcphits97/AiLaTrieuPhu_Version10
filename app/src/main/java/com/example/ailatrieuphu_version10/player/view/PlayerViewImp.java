package com.example.ailatrieuphu_version10.player.view;

import com.example.ailatrieuphu_version10.player.model.QuestionModel;

import java.util.List;

public interface PlayerViewImp {
    void getResult(boolean result, String rightAnswer);
    void moveToEndGameFragment();
    void showQuestion(List<QuestionModel> questionList);
    void cancelCountDownTimer();
}
