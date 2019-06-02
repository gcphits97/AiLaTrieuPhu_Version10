package com.example.ailatrieuphu_version10.login.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.ailatrieuphu_version10.login.fragment.LoginFragment;
import com.example.ailatrieuphu_version10.login.session.LoginSession;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.highScore.HighScoreActivity;
import com.example.ailatrieuphu_version10.information.InformationActivity;
import com.example.ailatrieuphu_version10.player.view.PlayerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnChoiNgay, btnDiemCao, btnThongTin, btnDangNhap, btnDangXuat, btnChoi;
    private LinearLayout khungDangNhap;
    private FrameLayout frameBackgroundMain;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set full man hinh
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
        setContentView(R.layout.main_activity);

        AnhXa();

        if (LoginSession.getLoggedInStatus(getApplicationContext())) {
            khungDangNhap.setVisibility(View.GONE);
            btnChoi.setVisibility(View.VISIBLE);
            btnDangXuat.setVisibility(View.VISIBLE);
        } else {
            khungDangNhap.setVisibility(View.VISIBLE);
            btnChoi.setVisibility(View.GONE);
            btnDangXuat.setVisibility(View.GONE);
        }

        //set su kien click
        btnDangNhap.setOnClickListener(this);
        btnChoiNgay.setOnClickListener(this);
        btnDiemCao.setOnClickListener(this);
        btnThongTin.setOnClickListener(this);
        btnDangXuat.setOnClickListener(this);
        btnChoi.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.opening);
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    public void AnhXa() {
        btnChoiNgay = findViewById(R.id.btnChoiNgay);
        btnDiemCao = findViewById(R.id.btnDiemCao);
        btnThongTin = findViewById(R.id.btnThongTin);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        khungDangNhap = findViewById(R.id.khungDangNhap);
        btnChoi = findViewById(R.id.btnChoi);
        frameBackgroundMain = findViewById(R.id.frameBackgroundMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhap:
                frameBackgroundMain.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentParentMain, new LoginFragment()).commit();
                btnDangNhap.setEnabled(false);
                btnChoiNgay.setEnabled(false);
                btnDiemCao.setEnabled(false);
                btnThongTin.setEnabled(false);
                break;
            case R.id.btnChoiNgay:
                startActivity(new Intent(MainActivity.this, PlayerActivity.class));
                finish();
                break;
            case R.id.btnChoi:
                startActivity(new Intent(MainActivity.this, PlayerActivity.class));
                finish();
                break;
            case R.id.btnDiemCao:
                startActivity(new Intent(MainActivity.this, HighScoreActivity.class));
                break;
            case R.id.btnThongTin:
                startActivity(new Intent(MainActivity.this, InformationActivity.class));
                break;
            case R.id.btnDangXuat:
                khungDangNhap.setVisibility(View.VISIBLE);
                btnChoi.setVisibility(View.GONE);
                btnDangXuat.setVisibility(View.GONE);
                LoginSession.setLoggedIn(getApplicationContext(), false, 0, null, null);
                break;
        }
    }
}
