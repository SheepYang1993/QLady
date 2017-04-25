package me.sheepyang.qlady.activity.mine;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }

    @Override
    @OnClick({R.id.btn_send})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
