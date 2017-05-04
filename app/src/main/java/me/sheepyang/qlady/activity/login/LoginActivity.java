package me.sheepyang.qlady.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.EncryptUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.BuildConfig;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.http.Api;
import me.sheepyang.qlady.util.ExceptionUtil;
import me.sheepyang.qlady.widget.ClearEditText;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edt_phone)
    ClearEditText mEdtPhone;
    @BindView(R.id.edt_password)
    ClearEditText mEdtPassword;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.IS_DEBUG) {
            mEdtPhone.setText("18759608982");
            mEdtPassword.setText("2");
        }
    }

    @Override
    @OnClick({R.id.btn_login, R.id.tv_no_account, R.id.tv_register_now, R.id.tv_forgot_password})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                toLogin();
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

    private void toLogin() {
        if (TextUtils.isEmpty(mEdtPhone.getText().toString().trim())) {
            showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(mEdtPassword.getText().toString().trim())) {
            showToast("请输入密码");
            return;
        }
        OkGo.post(Api.LOGIN)
                .tag(this)
                .params("phone", mEdtPhone.getText().toString().trim())
                .params("password", EncryptUtils.encryptMD5ToString(mEdtPassword.getText().toString().trim()).toLowerCase())
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(String baseResponse, Call call, Response response) {

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ExceptionUtil.handleException(mContext, response, e);
                    }
                });
    }
}
