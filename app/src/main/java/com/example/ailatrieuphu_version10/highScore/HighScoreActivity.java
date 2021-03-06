package com.example.ailatrieuphu_version10.highScore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.login.session.LoginSession;
import com.example.ailatrieuphu_version10.login.view.MainActivity;
import com.example.ailatrieuphu_version10.retrofit.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighScoreActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBangXepHang;
    Toolbar toolbarBangXepHang;
    ImageButton imageButtonBack;
    private HighScoreAdapter highScoreAdapter;
    LinearLayout playerHighScore;
    TextView txtSTTPlayer, txtUsernamePlayer, txtSoCauTraLoiDungPlayer, txtBestRewardPlayer, txtTimePlayer, txtTyLeTraLoiDungPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.high_score_activity);

        recyclerViewBangXepHang = findViewById(R.id.recyclerViewBangXepHang);
        toolbarBangXepHang = findViewById(R.id.toolbarBangXepHang);
        imageButtonBack = findViewById(R.id.imageButtonBack);
        playerHighScore = findViewById(R.id.playerHighScore);
        txtSTTPlayer = findViewById(R.id.txtSTTPlayer);
        txtUsernamePlayer = findViewById(R.id.txtUsernamePlayer);
        txtSoCauTraLoiDungPlayer = findViewById(R.id.txtSoCauTraLoiDungPlayer);
        txtBestRewardPlayer = findViewById(R.id.txtBestRewardPlayer);
        txtTimePlayer = findViewById(R.id.txtTimePlayer);
        txtTyLeTraLoiDungPlayer = findViewById(R.id.txtTyLeTraLoiDungPlayer);

        getSupportActionBar();
        toolbarBangXepHang.setTitle("");

        Call<List<HighScoreModel>> call = APIClient.highScoreCRU().getPlayerList();

        call.enqueue(new Callback<List<HighScoreModel>>() {
            @Override
            public void onResponse(Call<List<HighScoreModel>> call, Response<List<HighScoreModel>> response) {
                List<HighScoreModel> highScoreModelList = response.body();
                if (highScoreModelList != null) {
                    highScoreAdapter = new HighScoreAdapter(getApplicationContext(), highScoreModelList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerViewBangXepHang.setLayoutManager(layoutManager);
                    recyclerViewBangXepHang.setAdapter(highScoreAdapter);
                    highScoreAdapter.notifyDataSetChanged();


                    for (int i = 0; i < highScoreModelList.size(); i++) {
                        HighScoreModel highScoreModel = highScoreModelList.get(i);
                        if (highScoreModel.getUserModel().getId() == LoginSession.getId(getApplicationContext())) {
                            txtSTTPlayer.setText(String.valueOf(i + 1));
                            txtBestRewardPlayer.setText(highScoreModel.getBestreward());
                            txtSoCauTraLoiDungPlayer.setText(String.valueOf(highScoreModel.getBestplay()));
                            txtTimePlayer.setText(highScoreModel.getPlaytime());
                            txtTyLeTraLoiDungPlayer.setText(String.valueOf(highScoreModel.getTyle()));
                            txtUsernamePlayer.setText(highScoreModel.getUserModel().getUsername());
                        }
                    }
                } else {
                    Toast.makeText(HighScoreActivity.this, getText(R.string.error_high_score), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<HighScoreModel>> call, Throwable t) {
                Toast.makeText(HighScoreActivity.this, getText(R.string.error_put_data) + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighScoreActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
