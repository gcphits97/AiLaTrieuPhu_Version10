package com.example.ailatrieuphu_version10.player.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.player.view.PlayerViewImp;
import com.plattysoft.leonids.ParticleSystem;

import java.util.Objects;

public class VictoryFragment extends Fragment {
    TextView txtReward, txtVictory;
    Button btnOk;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View myView = LayoutInflater.from(getContext()).inflate(R.layout.victory_fragment, null, false);

        txtReward = myView.findViewById(R.id.txtReward);
        txtVictory = myView.findViewById(R.id.txtVictory);
        btnOk = myView.findViewById(R.id.btnOk);


        txtReward.setText(getText(R.string.p150m)+" vnđ");

        new ParticleSystem(Objects.requireNonNull(getActivity()), 80, R.drawable.confeti_left, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(txtVictory, 8);

        new ParticleSystem(Objects.requireNonNull(getActivity()), 80, R.drawable.confeti_right, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(txtVictory, 8);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("socau", "15");
                bundle.putString("giaithuong", "150.000.000");
                EndGameFragment endGameFragment = new EndGameFragment();
                endGameFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParent, endGameFragment).commit();
            }
        });

        return myView;
    }
}
