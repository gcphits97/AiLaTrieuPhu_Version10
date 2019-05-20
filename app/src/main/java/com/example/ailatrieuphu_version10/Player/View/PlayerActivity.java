package com.example.ailatrieuphu_version10.Player.View;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.Player.Model.QuestionModel;
import com.example.ailatrieuphu_version10.Player.Presenter.PlayerPresenterModel;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.Player.Fragment.EndGameFragment;
import com.example.ailatrieuphu_version10.Player.Fragment.StopPlayingFragment;

import java.util.List;
import java.util.Locale;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, PlayerViewImp {
    private TextView txt5050, txtTroGiupTuKhanGia, txtGoiDienChoNguoiThan, txtCountDownTimer, txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC,
            txtAnswerD, txtDungCuocChoi;
    private LinearLayout fragmentParent;
    private FrameLayout frameBackground;
    private int x = 0, result = 0, reward = -1;
    private MediaPlayer openingMusic, gameMusic;
    private static int GAME_TIME = 3000;
    private static final int[] idArray = {R.id.txtReward200k, R.id.txtReward400k, R.id.txtReward600k, R.id.txtReward1m, R.id.txtReward2m
            , R.id.txtReward3m, R.id.txtReward6m, R.id.txtReward10m, R.id.txtReward14m, R.id.txtReward22m
            , R.id.txtReward30m, R.id.txtReward40m, R.id.txtReward60m, R.id.txtReward85m, R.id.txtReward150m};
    private TextView[] textViews = new TextView[idArray.length];

    private static final long START_TIME_IN_MILLIS = 30000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    int MUSIC_TIME_OPENING = 3000;

    private PlayerPresenterModel playerPresenterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.player_activity);

        AnhXa();
        playerPresenterModel = new PlayerPresenterModel(getApplicationContext(), this);
        playerPresenterModel.setEventClickAnswer(txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD);

        txtDungCuocChoi.setOnClickListener(this);
        txtAnswerA.setOnClickListener(this);
        txtAnswerB.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        openingMusic = MediaPlayer.create(getApplicationContext(), R.raw.start_game);
        openingMusic.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gameMusic = MediaPlayer.create(getApplicationContext(), R.raw.wait_time);
                gameMusic.start();
            }
        }, MUSIC_TIME_OPENING);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        openingMusic.stop();
    }

//    public String checkDigit(int number) {
//        return number < 10 ? "0"+number : String.valueOf(number);
//    }

    public void AnhXa() {
        txt5050 = findViewById(R.id.txt5050);
        txtTroGiupTuKhanGia = findViewById(R.id.txtTroGiupTuKhanGia);
        txtGoiDienChoNguoiThan = findViewById(R.id.txtGoiDienChoNguoiThan);
        txtCountDownTimer = findViewById(R.id.txtCountDownTimer);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtAnswerA = findViewById(R.id.txtAnswerA);
        txtAnswerB = findViewById(R.id.txtAnswerB);
        txtAnswerC = findViewById(R.id.txtAnswerC);
        txtAnswerD = findViewById(R.id.txtAnswerD);
        txtDungCuocChoi = findViewById(R.id.txtDungCuocChoi);
        fragmentParent = findViewById(R.id.fragmentParent);
        frameBackground = findViewById(R.id.frameBackground);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtDungCuocChoi:
                //pauseTime();
                txtAnswerA.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerB.setEnabled(false);
                frameBackground.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, new StopPlayingFragment()).commit();
                break;
            case R.id.txtAnswerA:
                txtAnswerA.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerA.setEnabled(false);
                result = 1;
//                pauseTime();
                break;
            case R.id.txtAnswerB:
                playerPresenterModel.setEventCheckAnswer();
                txtAnswerB.setBackgroundResource(R.drawable.question_choice);
                txtAnswerA.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerB.setEnabled(false);
                result = 2;
//                pauseTime();
                break;
            case R.id.txtAnswerC:
                txtAnswerC.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerA.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerC.setEnabled(false);
                result = 3;
//                pauseTime();
                break;
            case R.id.txtAnswerD:
                txtAnswerD.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerA.setEnabled(false);
                txtAnswerD.setEnabled(false);
                result = 4;
//                pauseTime();
                break;
        }
    }

//    public void startTime() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//                frameBackground.setVisibility(View.VISIBLE);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, new EndGameFragment()).commit();
//            }
//        }.start();
//    }
//
//    public void pauseTime() {
//        mCountDownTimer.cancel();
//    }
//
//    public void updateCountDownText() {
//        int second = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", second);
//        txtCountDownTimer.setText(timeLeftFormatted);
//    }
//
//    public void resetTime() {
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        startTime();
//    }

    @Override
    public void getResultEventClickAnswer() {

    }

    @Override
    public void getResultEventCheckAnswer() {

    }
}
