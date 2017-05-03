package me.sheepyang.qlady.activity.login;

import android.os.Bundle;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.widget.ClearEditText;
import okhttp3.Call;
import okhttp3.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edt_get_code)
    ClearEditText mEdtGetCode;

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
                OkGo.post("http://172.21.20.2:8080/GodGoddess/tBaseUser/getAcode.action")
                        .tag(this)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                showToast(s);
                            }
                        });
                break;
            case R.id.btn_reset_password:
                OkGo.post("http://172.21.20.2:8080/GodGoddess/tBaseUser/getPassword.action")
                        .tag(this)
                        .params("e.phone", "18759608982")
                        .params("e.acode", mEdtGetCode.getText().toString())
                        .params("e.password", "2")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                setResult(RESULT_OK);
                                onBackPressed();
                            }
                        });
                break;
            default:
                break;
        }
    }
}
