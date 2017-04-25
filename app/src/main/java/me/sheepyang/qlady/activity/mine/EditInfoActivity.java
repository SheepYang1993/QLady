package me.sheepyang.qlady.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.widget.QBar;

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.q_bar)
    QBar mQBar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {

    }

    private void initListener() {
        mQBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    @OnClick({R.id.rl_edit_avatar, R.id.ll_edit_password, R.id.ll_edit_name})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit_avatar:
                showToast("修改头像");
                break;
            case R.id.ll_edit_password:
                startActivity(new Intent(mContext, EditPasswordActivity.class));
                break;
            case R.id.ll_edit_name:
                startActivity(new Intent(mContext, EditNameActivity.class));
                break;
            default:
                break;
        }
    }
}
