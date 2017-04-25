package me.sheepyang.qlady.activity.mine;

import android.os.Bundle;

import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;

public class EditPasswordActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_edit_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }
}
