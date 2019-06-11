package com.example.ailatrieuphu_version10.information;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ailatrieuphu_version10.BuildConfig;
import com.example.ailatrieuphu_version10.R;
import com.example.ailatrieuphu_version10.login.view.MainActivity;

public class InformationActivity extends AppCompatActivity {
    TextView txtGameVersion;
    ImageButton imageButtonBack;
    Toolbar toolbarThongTin;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.information_activity);
        txtGameVersion = findViewById(R.id.txtGameVersion);
        imageButtonBack = findViewById(R.id.imageButtonBack);
        toolbarThongTin = findViewById(R.id.toolbarThongTin);

        getSupportActionBar();
        toolbarThongTin.setTitle("");

//        try {
//            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
//            txtGameVersion.setText("Game version: "+packageInfo.versionName);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

        //hoặc dùng build config
        txtGameVersion.setText("Game version: "+BuildConfig.VERSION_NAME);

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformationActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
