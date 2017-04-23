package me.sheepyang.qlady.activity;

import android.os.Bundle;

import me.sheepyang.qlady.R;

public class PersonCenterActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_person_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }
}
