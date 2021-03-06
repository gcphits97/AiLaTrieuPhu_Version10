package com.example.ailatrieuphu_version10.player.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.player.fragment.CallPhoneFragment;
import com.example.ailatrieuphu_version10.player.fragment.SpectatorFragment;
import com.example.ailatrieuphu_version10.player.fragment.VictoryFragment;
import com.example.ailatrieuphu_version10.player.model.QuestionModel;
import com.example.ailatrieuphu_version10.player.presenter.PlayerPresenterModel;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.player.fragment.EndGameFragment;
import com.example.ailatrieuphu_version10.player.fragment.StopPlayingFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, PlayerViewImp {
    private TextView txt5050, txtTroGiupTuKhanGia, txtGoiDienChoNguoiThan, txtCountDownTimer, txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC,
            txtAnswerD, txtDungCuocChoi;
    private FrameLayout frameBackground;

    //countdown timer
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 30000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimeRunning;
//    private long mEndTime;
    // ------------------------------------------

    private static int i;
    private int reward;

    private static final int[] idArray = {R.id.txtReward200k, R.id.txtReward400k, R.id.txtReward600k, R.id.txtReward1m, R.id.txtReward2m
            , R.id.txtReward3m, R.id.txtReward6m, R.id.txtReward10m, R.id.txtReward14m, R.id.txtReward22m
            , R.id.txtReward30m, R.id.txtReward40m, R.id.txtReward60m, R.id.txtReward85m, R.id.txtReward150m};
    private TextView[] textViews = new TextView[idArray.length];

    private MediaPlayer openingMusic, gameMusic;
    int MUSIC_TIME_OPENING = 3000;
    private static int GAME_TIME = 3000;

    private List<QuestionModel> questionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.player_activity);

        AnhXa();

        i = 0;
        reward = -1;

        PlayerPresenterModel playerPresenterModel = new PlayerPresenterModel(getApplicationContext(), this);
        playerPresenterModel.getQuestionList();
        questionModelList = new ArrayList<>();
        updateCountDownText(txtCountDownTimer);

        txtDungCuocChoi.setOnClickListener(this);
        txtGoiDienChoNguoiThan.setOnClickListener(this);
        txt5050.setOnClickListener(this);
        txtTroGiupTuKhanGia.setOnClickListener(this);
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
                gameMusic.setLooping(true);
            }
        }, MUSIC_TIME_OPENING);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameMusic.stop();
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
        frameBackground = findViewById(R.id.frameBackground);
    }

    @Override
    public void onClick(View v) {
        String choice;
        switch (v.getId()) {
            case R.id.txtDungCuocChoi:
                Bundle bundle = new Bundle();
                StopPlayingFragment stopPlayingFragment = new StopPlayingFragment(this);
                bundle.putString("socau", String.valueOf(i));
                if (i == 0) {
                    bundle.putString("giaithuong", "0");
                } else {
                    bundle.putString("giaithuong", textViews[reward].getText().toString());
                }

                stopPlayingFragment.setArguments(bundle);

                txtAnswerA.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerB.setEnabled(false);
                frameBackground.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, stopPlayingFragment).commit();
                break;
            case R.id.txtAnswerA:
                choice = "A";
                txtAnswerA.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerA.setEnabled(false);
                pauseTime();
                checkResult(choice);
                break;
            case R.id.txtAnswerB:
                choice = "B";
                txtAnswerB.setBackgroundResource(R.drawable.question_choice);
                txtAnswerA.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerB.setEnabled(false);
                pauseTime();
                checkResult(choice);
                break;
            case R.id.txtAnswerC:
                choice = "C";
                txtAnswerC.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerA.setEnabled(false);
                txtAnswerD.setEnabled(false);
                txtAnswerC.setEnabled(false);
                pauseTime();
                checkResult(choice);
                break;
            case R.id.txtAnswerD:
                choice = "D";
                txtAnswerD.setBackgroundResource(R.drawable.question_choice);
                txtAnswerB.setEnabled(false);
                txtAnswerC.setEnabled(false);
                txtAnswerA.setEnabled(false);
                txtAnswerD.setEnabled(false);
                pauseTime();
                checkResult(choice);
                break;
            case R.id.txtGoiDienChoNguoiThan:
                setCallPhoneEvent();
                break;
            case R.id.txt5050:
                setEventFiftyPercent();
                break;
            case R.id.txtTroGiupTuKhanGia:
                frameBackground.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, new SpectatorFragment()).commit();
                break;
        }
    }

    private void setEventFiftyPercent() {
        if (questionModelList.size() != 0) {
            txt5050.setBackgroundResource(R.drawable.unfiftypercent);
            txt5050.setEnabled(false);
            String answer = questionModelList.get(i).getRightanswer();
            if (answer.equals("A")) {
                txtAnswerB.setEnabled(false);
                txtAnswerB.setText("");
                txtAnswerC.setEnabled(false);
                txtAnswerC.setText("");
            } else if (answer.equals("B")) {
                txtAnswerD.setEnabled(false);
                txtAnswerD.setText("");
                txtAnswerC.setEnabled(false);
                txtAnswerC.setText("");
            } else if (answer.equals("C")) {
                txtAnswerA.setEnabled(false);
                txtAnswerA.setText("");
                txtAnswerD.setEnabled(false);
                txtAnswerD.setText("");
            } else {
                txtAnswerB.setEnabled(false);
                txtAnswerB.setText("");
                txtAnswerA.setEnabled(false);
                txtAnswerA.setText("");
            }
        }
    }

    private void setCallPhoneEvent() {
        String talkingResult = "";
        if (questionModelList.size() != 0) {
            txtGoiDienChoNguoiThan.setBackgroundResource(R.drawable.untelephone);
            txtGoiDienChoNguoiThan.setEnabled(false);
            txtAnswerB.setEnabled(false);
            txtAnswerC.setEnabled(false);
            txtAnswerA.setEnabled(false);
            txtAnswerD.setEnabled(false);
            String resultQuestion = questionModelList.get(i).getRightanswer();
            Random random = new Random();
            int i = random.nextInt(5);
            switch (i) {
                case 0:
                    talkingResult = "Rất tiếc, tôi không biết câu trả lời của câu hỏi này :( Chúc bạn may mắn.";
                    break;

                case 1:
                    talkingResult = "Tôi đã tìm kiếm kết quả trên Google ^^ Tin tôi đi. Đáp án là " + resultQuestion;
                    break;

                case 2:
                    talkingResult = "Tôi không chắc lắm nhưng có lẽ đáp án là " + resultQuestion;
                    break;

                case 3:
                    talkingResult = "Nếu tôi là bạn thì tôi sẽ chọn " + resultQuestion + ". Nhưng tôi không phải là bạn :v";
                    break;

                case 4:
                    talkingResult = "Tôi khuyên bạn chọn đáp án " + resultQuestion + ". Chúc bạn may mắn!";
                    break;
            }
            Bundle bundle = new Bundle();
            CallPhoneFragment callPhoneFragment = new CallPhoneFragment();
            bundle.putString("text", talkingResult);
            callPhoneFragment.setArguments(bundle);
            frameBackground.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, callPhoneFragment).commit();
        } else {
            Toast.makeText(this, getText(R.string.internet_connection_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getResult(final boolean result, final String rightAnswer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (rightAnswer) {
                    case "A":
                        txtAnswerA.setBackgroundResource(R.drawable.anwser_background);
                        break;
                    case "B":
                        txtAnswerB.setBackgroundResource(R.drawable.anwser_background);
                        break;
                    case "C":
                        txtAnswerC.setBackgroundResource(R.drawable.anwser_background);
                        break;
                    case "D":
                        txtAnswerD.setBackgroundResource(R.drawable.anwser_background);
                        break;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (result) {
                            ++reward;
                            textViews[reward] = findViewById(idArray[reward]);
                            textViews[reward].setBackgroundResource(R.drawable.question_choice);
                            if (reward >= 1) {
                                textViews[reward - 1].setBackgroundResource(R.drawable.question_background);
                            }
                            if (i != 14) {
                                i++;
                                showQuestion(questionModelList);
                            } else {
                                VictoryFragment victoryFragment = new VictoryFragment();
                                frameBackground.setVisibility(View.VISIBLE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, victoryFragment).commit();
                            }
                        } else {
                            moveToEndGameFragment();
                        }
                    }
                }, GAME_TIME);
            }
        }, GAME_TIME);
    }

    @Override
    public void moveToEndGameFragment() {
        Bundle bundle = new Bundle();
        EndGameFragment endGameFragment = new EndGameFragment();
        if (i > 0 && i <= 4) {
            bundle.putString("socau", String.valueOf(i));
            bundle.putString("giaithuong", "200.000");
            endGameFragment.setArguments(bundle);
        } else if (i > 4 && i <= 9) {
            bundle.putString("socau", String.valueOf(i));
            bundle.putString("giaithuong", "2.000.000");
            endGameFragment.setArguments(bundle);
        } else if (i > 9) {
            bundle.putString("socau", String.valueOf(i));
            bundle.putString("giaithuong", "22.000.000");
            endGameFragment.setArguments(bundle);
        } else {
            bundle.putString("socau", String.valueOf(i));
            bundle.putString("giaithuong", "0");
            endGameFragment.setArguments(bundle);
        }
        frameBackground.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, endGameFragment).commit();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showQuestion(List<QuestionModel> questionList) {
        questionModelList = questionList;

        resetTime(txtCountDownTimer);
        txtQuestion.setText(questionModelList.get(i).getQuestion());
        txtAnswerA.setText("A: " + questionModelList.get(i).getAnswera());
        txtAnswerB.setText("B: " + questionModelList.get(i).getAnswerb());
        txtAnswerC.setText("C: " + questionModelList.get(i).getAnswerc());
        txtAnswerD.setText("D: " + questionModelList.get(i).getAnswerd());

        txtAnswerB.setEnabled(true);
        txtAnswerC.setEnabled(true);
        txtAnswerD.setEnabled(true);
        txtAnswerA.setEnabled(true);
        txtAnswerA.setBackgroundResource(R.drawable.question_background);
        txtAnswerB.setBackgroundResource(R.drawable.question_background);
        txtAnswerC.setBackgroundResource(R.drawable.question_background);
        txtAnswerD.setBackgroundResource(R.drawable.question_background);

        txtAnswerA.setOnClickListener(this);
        txtAnswerB.setOnClickListener(this);
        txtAnswerC.setOnClickListener(this);
        txtAnswerD.setOnClickListener(this);
    }

    @Override
    public void cancelCountDownTimer() {
        if (mTimeRunning) {
            pauseTime();
        } else {
            Toast.makeText(this, getText(R.string.error_get_question), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkResult(String choice) {
        if (questionModelList.get(i).getRightanswer().equals(choice)) {
            getResult(true, questionModelList.get(i).getRightanswer());
        } else {
            getResult(false, questionModelList.get(i).getRightanswer());
        }
    }
//-------- start configuration countdown timer --------//
    public void startTime(final TextView txtCountDownTimer) {
//        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText(txtCountDownTimer);
            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                moveToEndGameFragment();
            }
        }.start();

        mTimeRunning = true;
    }

    public void pauseTime() {
        mCountDownTimer.cancel();
        mTimeRunning = false;
    }

    public void updateCountDownText(TextView txtCountDownTimer) {
        int second = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", second);
        txtCountDownTimer.setText(timeLeftFormatted);
    }

    public void resetTime(TextView txtCountDownTimer) {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText(txtCountDownTimer);
        startTime(txtCountDownTimer);
    }
//-------- end configuration countdown timer --------//

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT || newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            openingMusic.stop();
//            gameMusic.stop();
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putLong("millisLeft", mTimeLeftInMillis);
//        outState.putBoolean("timeRunning", mTimeRunning);
//        outState.putLong("endTime", mEndTime);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
//        mTimeRunning = savedInstanceState.getBoolean("timeRunning");
//        updateCountDownText(txtCountDownTimer);
//
//        if (mTimeRunning) {
//            mEndTime = savedInstanceState.getLong("endTime");
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//            startTime(txtCountDownTimer);
//        }
//    }
}
