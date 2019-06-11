package com.example.ailatrieuphu_version10.highScore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.R;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHoder> {

    Context context;
    List<HighScoreModel> highScoreModelList;

    public HighScoreAdapter(Context context, List<HighScoreModel> highScoreModelList) {
        this.context = context;
        this.highScoreModelList = highScoreModelList;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        LinearLayout background;
        TextView txtSTT, txtUsername, txtSoCauTraLoiDung, txtBestReward, txtTime, txtTyLeTraLoiDung;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.background);
            txtSTT = itemView.findViewById(R.id.txtSTT);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtSoCauTraLoiDung = itemView.findViewById(R.id.txtSoCauTraLoiDung);
            txtBestReward = itemView.findViewById(R.id.txtBestReward);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtTyLeTraLoiDung = itemView.findViewById(R.id.txtTyLeTraLoiDung);
        }
    }
    @NonNull
    @Override
    public HighScoreAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHoder(LayoutInflater.from(context).inflate(R.layout.high_score_custom, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreAdapter.ViewHoder viewHoder, int i) {
        HighScoreModel highScoreModel = highScoreModelList.get(i);

        viewHoder.background.setBackgroundResource(R.color.colorPrimaryTransparent);
        viewHoder.txtSTT.setText(String.valueOf(i+1));
        viewHoder.txtUsername.setText(highScoreModel.getUserModel().getUsername());
        viewHoder.txtSoCauTraLoiDung.setText(String.valueOf(highScoreModel.getBestplay()));
        viewHoder.txtBestReward.setText(highScoreModel.getBestreward());
        viewHoder.txtTime.setText(highScoreModel.getPlaytime());
        viewHoder.txtTyLeTraLoiDung.setText(String.valueOf(highScoreModel.getTyle()));
    }

    @Override
    public int getItemCount() {
        return highScoreModelList.size();
    }
}
