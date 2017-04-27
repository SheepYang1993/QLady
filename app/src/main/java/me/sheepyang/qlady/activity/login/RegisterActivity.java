package me.sheepyang.qlady.activity.login;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @OnClick({R.id.tv_get_verify_code, R.id.btn_register, R.id.tv_has_account, R.id.tv_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verify_code:
                showToast("获取验证码");
                break;
            case R.id.btn_register:
                setResult(RESULT_OK);
                onBackPressed();
                break;
            case R.id.tv_login:
            case R.id.tv_has_account:
                setResult(RESULT_OK);
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
