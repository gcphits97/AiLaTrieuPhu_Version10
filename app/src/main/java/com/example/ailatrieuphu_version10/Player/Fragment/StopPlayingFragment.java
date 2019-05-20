package com.example.ailatrieuphu_version10.Player.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.Player.View.PlayerActivity;

import java.util.Objects;

public class StopPlayingFragment extends Fragment implements View.OnClickListener {
    TextView txtYes, txtNo;
    PlayerActivity playerActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View myView = LayoutInflater.from(getContext()).inflate(R.layout.stopplaying_fragment, null, false);
        playerActivity = new PlayerActivity();
        txtYes = myView.findViewById(R.id.txtYes);
        txtNo = myView.findViewById(R.id.txtNo);

        txtYes.setOnClickListener(this);
        txtNo.setOnClickListener(this);

        return myView;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtYes:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, new EndGameFragment()).commit();
                break;
            case R.id.txtNo:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(this).commit();
                break;
        }
    }
}
