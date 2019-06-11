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
import android.widget.Toast;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.player.view.PlayerViewImp;

import java.util.Objects;

@SuppressLint("ValidFragment")
public class StopPlayingFragment extends Fragment implements View.OnClickListener {
    TextView txtYes, txtNo, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    private FrameLayout frameBackground;
    PlayerViewImp playerViewImp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View myView = LayoutInflater.from(getContext()).inflate(R.layout.stopplaying_fragment, null, false);
        txtYes = myView.findViewById(R.id.txtYes);
        txtNo = myView.findViewById(R.id.txtNo);

        txtAnswerA = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerA);
        txtAnswerB = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerB);
        txtAnswerC = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerC);
        txtAnswerD = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerD);
        frameBackground = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackground);

        txtYes.setOnClickListener(this);
        txtNo.setOnClickListener(this);

        return myView;
    }

    @SuppressLint("ValidFragment")
    public StopPlayingFragment(PlayerViewImp playerViewImp) {
        this.playerViewImp = playerViewImp;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtYes:
                if (getArguments() != null) {
                    playerViewImp.cancelCountDownTimer();
                    Bundle bundle = new Bundle();
                    bundle.putString("socau", getArguments().getString("socau"));
                    bundle.putString("giaithuong", getArguments().getString("giaithuong"));
                    EndGameFragment endGameFragment = new EndGameFragment();
                    endGameFragment.setArguments(bundle);
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, endGameFragment).commit();
                } else {
                    Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getText(R.string.error_put_data), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.txtNo:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(this).commit();
                frameBackground.setVisibility(View.GONE);
                txtAnswerA.setEnabled(true);
                txtAnswerB.setEnabled(true);
                txtAnswerC.setEnabled(true);
                txtAnswerD.setEnabled(true);
                break;
        }
    }
}
