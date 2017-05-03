package me.sheepyang.qlady.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.EncryptUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import okhttp3.Call;
import okhttp3.Response;

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
                OkGo.post("http://172.21.20.2:8080/GodGoddess/_check")
                        .tag(this)
                        .params("phone", "18759608982")
                        .params("password", "2")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mApp.setLogin(true);
                                setResult(RESULT_OK);
                                onBackPressed();
                            }
                        });
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
