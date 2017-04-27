package me.sheepyang.qlady.activity.login;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @OnClick({R.id.iv_left, R.id.tv_get_verify_code, R.id.btn_reset_password})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.tv_get_verify_code:
                showToast("获取验证码");
                break;
            case R.id.btn_reset_password:
                setResult(RESULT_OK);
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
