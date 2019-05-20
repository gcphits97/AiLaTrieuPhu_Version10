package com.example.ailatrieuphu_version10.Player.Presenter;

import android.widget.TextView;

public interface PlayerPresenterImp {
    void setEventClickAnswer(TextView txtQuestion, TextView txtAnswerA, TextView txtAnswerB, TextView txtAnswerC, TextView txtAnswerD);
    void setEventCheckAnswer();
}
