package com.example.ailatrieuphu_version10.player.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.player.view.PlayerActivity;
import com.example.ailatrieuphu_version10.R;

import java.util.Objects;

public class StopPlayingFragment extends Fragment implements View.OnClickListener {
    TextView txtYes, txtNo, txtCountDownTimer, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    PlayerActivity playerActivity;
    private FrameLayout frameBackground;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View myView = LayoutInflater.from(getContext()).inflate(R.layout.stopplaying_fragment, null, false);
        txtYes = myView.findViewById(R.id.txtYes);
        txtNo = myView.findViewById(R.id.txtNo);

        txtCountDownTimer = Objects.requireNonNull(getActivity()).findViewById(R.id.txtCountDownTimer);
        txtAnswerA = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerA);
        txtAnswerB = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerB);
        txtAnswerC = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerC);
        txtAnswerD = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerD);
        frameBackground = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackground);

        playerActivity = new PlayerActivity();

        txtYes.setOnClickListener(this);
        txtNo.setOnClickListener(this);

        return myView;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtYes:
                if (getArguments() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("socau", getArguments().getString("socau"));
                    bundle.putString("giaithuong", getArguments().getString("giaithuong"));
                    EndGameFragment endGameFragment = new EndGameFragment();
                    endGameFragment.setArguments(bundle);
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, endGameFragment).commit();
                }
                break;
            case R.id.txtNo:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(this).commit();
                frameBackground.setVisibility(View.GONE);
                playerActivity.resumeTime(txtCountDownTimer);
                txtAnswerA.setEnabled(true);
                txtAnswerB.setEnabled(true);
                txtAnswerC.setEnabled(true);
                txtAnswerD.setEnabled(true);
                break;
        }
    }
}
