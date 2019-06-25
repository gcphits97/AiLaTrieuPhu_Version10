package com.example.ailatrieuphu_version10.player.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.R;

import java.util.Objects;

public class CallPhoneFragment extends Fragment {
    TextView txtCallPhone, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    private FrameLayout frameBackground;
    Button btnOk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = LayoutInflater.from(getContext()).inflate(R.layout.call_phone_fragment, null, false);

        txtCallPhone = myView.findViewById(R.id.txtCallPhone);
        btnOk = myView.findViewById(R.id.btnOk);

        txtAnswerA = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerA);
        txtAnswerB = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerB);
        txtAnswerC = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerC);
        txtAnswerD = Objects.requireNonNull(getActivity()).findViewById(R.id.txtAnswerD);
        frameBackground = Objects.requireNonNull(getActivity()).findViewById(R.id.frameBackground);

        assert getArguments() != null;
        String text = getArguments().getString("text");
        txtCallPhone.setText(text);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().hide(CallPhoneFragment.this).commit();
                frameBackground.setVisibility(View.GONE);
                txtAnswerA.setEnabled(true);
                txtAnswerB.setEnabled(true);
                txtAnswerC.setEnabled(true);
                txtAnswerD.setEnabled(true);
            }
        });

        return myView;
    }
}
