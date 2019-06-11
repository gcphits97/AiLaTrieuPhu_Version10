package com.example.ailatrieuphu_version10.player.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.highScore.HighScoreModel;
import com.example.ailatrieuphu_version10.login.session.LoginSession;
import com.example.ailatrieuphu_version10.login.view.MainActivity;
import com.example.ailatrieuphu_version10.player.view.PlayerActivity;
import com.example.ailatrieuphu_version10.retrofit.APIClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndGameFragment extends Fragment implements View.OnClickListener {
    TextView txtNumberOfCorrectAnswer, txtYourReward;
    ImageButton imgButtonHome, imgButtonRefresh;
    private String socau, giaithuong;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View myView = LayoutInflater.from(getContext()).inflate(R.layout.endgame_fragment, null, false);
        txtNumberOfCorrectAnswer = myView.findViewById(R.id.txtNumberOfCorrectAnswer);
        txtYourReward = myView.findViewById(R.id.txtYourReward);
        imgButtonHome = myView.findViewById(R.id.imgButtonHome);
        imgButtonRefresh = myView.findViewById(R.id.imgButtonRefresh);

        if (getArguments() != null) {
            socau = getArguments().getString("socau");
            giaithuong = getArguments().getString("giaithuong");
        } else {
            Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.error_put_data), Toast.LENGTH_LONG).show();
        }
        txtNumberOfCorrectAnswer.setText(socau);
        txtYourReward.setText(giaithuong+" vnđ");

        HighScoreModel highScoreModel = new HighScoreModel(LoginSession.getId(getContext()), Integer.parseInt(socau), giaithuong);
        Call<HighScoreModel> call = APIClient.highScoreCRU().createOrUpdateHighScore(highScoreModel);
        call.enqueue(new Callback<HighScoreModel>() {
            @Override
            public void onResponse(Call<HighScoreModel> call, Response<HighScoreModel> response) {
                Log.d("ahihi123", "onResponse: "+response.code());
            }

            @Override
            public void onFailure(Call<HighScoreModel> call, Throwable t) {
                Log.d("ahihi123", "onFailure: "+t.getMessage());
            }
        });

        imgButtonHome.setOnClickListener(this);
        imgButtonRefresh.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButtonHome:
                startActivity(new Intent(getContext(), MainActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.imgButtonRefresh:
                startActivity(new Intent(getContext(), PlayerActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
        }
    }


}
