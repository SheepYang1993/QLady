package me.sheepyang.qlady.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.EncryptUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.http.Api;
import me.sheepyang.qlady.util.ExceptionUtil;
import me.sheepyang.qlady.widget.ClearEditText;
import okhttp3.Call;
import okhttp3.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edt_get_code)
    ClearEditText mEdtGetCode;
    @BindView(R.id.edt_phone)
    ClearEditText mEdtPhone;
    @BindView(R.id.edt_password)
    ClearEditText mEdtPassword;
    @BindView(R.id.tv_get_verify_code)
    TextView mTvGetVerifyCode;
    private String mVerifyCode;
    private String mTempPhone;
    private int mCurrentTime;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentTime--;
            mTvGetVerifyCode.setText(mCurrentTime + "s");
            if (mCurrentTime > 0) {
                mHandler.sendEmptyMessageDelayed(0, 1000);
            } else {
                mTvGetVerifyCode.setEnabled(true);
                mTvGetVerifyCode.setText("获取验证码");
            }
        }
    };

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
                getVerifyCode();
                break;
            case R.id.btn_reset_password:
                resetPassword();
                break;
            default:
                break;
        }
    }

    private void resetPassword() {
        if (TextUtils.isEmpty(mEdtPhone.getText().toString().trim())) {
            showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(mVerifyCode)) {
            showToast("请等待获取验证码");
            return;
        }
        if (TextUtils.isEmpty(mEdtGetCode.getText().toString())) {
            showToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(mEdtPassword.getText().toString().trim())) {
            showToast("请输入密码");
            return;
        }
        if (!mTempPhone.equals(mEdtPhone.getText().toString().trim())) {
            showToast("手机号码已修改，请重新获取验证码");
            return;
        }
        if (!mVerifyCode.equals(mEdtGetCode.getText().toString())) {
            showToast("验证码不正确");
            return;
        }
        OkGo.post(Api.RESET_PASSWORD)
                .tag(this)
                .params("phone", mEdtPhone.getText().toString().trim())
                .params("e.acode", mEdtGetCode.getText().toString())
                .params("e.password", EncryptUtils.encryptMD5ToString(mEdtPassword.getText().toString().trim()).toLowerCase())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        setResult(RESULT_OK);
                        onBackPressed();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ExceptionUtil.handleException(mContext, response, e);
                    }
                });
    }

    private void getVerifyCode() {
        if (TextUtils.isEmpty(mEdtPhone.getText().toString().trim())) {
            showToast("请输入手机号码");
            return;
        }
        mTvGetVerifyCode.setEnabled(false);
        mCurrentTime = 10;
        mTvGetVerifyCode.setText(mCurrentTime + "s");
        mHandler.sendEmptyMessageDelayed(0, 1000);
        showToast("验证码已发送");
        mEdtGetCode.setText("");
        mVerifyCode = "";
        mTempPhone = mEdtPhone.getText().toString().trim();
        OkGo.post(Api.GET_VERIFY_CODE)
                .tag(this)
                .params("phone", mTempPhone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mVerifyCode = s;
                        showToast("验证码：" + mVerifyCode);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }
}
