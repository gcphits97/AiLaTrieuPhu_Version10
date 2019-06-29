package com.example.ailatrieuphu_version10.player.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.R;

public class SpectatorFragment extends Fragment {
    TextView txtTyLeA, txtTyLeB, txtTyLeC, txtTyLeD;
    ImageView imageViewA, imageViewB, imageViewC, imageViewD;
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = LayoutInflater.from(getContext()).inflate(R.layout.spectator_fragment, null, false);
        txtTyLeA = myView.findViewById(R.id.txtTyLeA);
        txtTyLeB = myView.findViewById(R.id.txtTyLeB);
        txtTyLeC = myView.findViewById(R.id.txtTyLeC);
        txtTyLeD = myView.findViewById(R.id.txtTyLeD);
        imageViewA = myView.findViewById(R.id.imageViewA);
        imageViewB = myView.findViewById(R.id.imageViewB);
        imageViewC = myView.findViewById(R.id.imageViewC);
        imageViewD = myView.findViewById(R.id.imageViewD);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageViewA.getLayoutParams();
        params.weight = 250;
        imageViewA.setLayoutParams(params);

        return myView;
    }
}
