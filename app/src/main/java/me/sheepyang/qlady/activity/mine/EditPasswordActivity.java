package me.sheepyang.qlady.activity.mine;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.widget.QBar;

public class EditPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.q_bar)
    QBar mQBar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_edit_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        mQBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {

    }

    @Override
    @OnClick({R.id.tv_get_verify_code})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verify_code:
                showToast("获取验证码");
                break;
            default:
                break;
        }
    }
}
