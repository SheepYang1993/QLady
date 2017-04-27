package me.sheepyang.qlady.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import me.sheepyang.qlady.R;

public class SplashActivity extends BaseActivity {
    private static Handler mHandler = new Handler();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                finish();
            }
        }, 1000);
    }
}
