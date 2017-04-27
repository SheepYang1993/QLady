package me.sheepyang.qlady.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @OnClick({R.id.btn_login, R.id.tv_no_account, R.id.tv_register_now, R.id.tv_forgot_password})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mApp.setLogin(true);
                setResult(RESULT_OK);
                onBackPressed();
                break;
            case R.id.tv_no_account:
            case R.id.tv_register_now:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;
            default:
                break;
        }
    }
}
