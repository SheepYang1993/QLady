package me.sheepyang.qlady.activity.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.widget.dialog.QDialog;

public class MineActivity extends BaseActivity implements View.OnClickListener {

    private QDialog mHintDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        mHintDialog.setOnRightClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApp.logout();
                onBackPressed();
            }
        });
    }

    private void initView() {
        mHintDialog = new QDialog(mContext);
        mHintDialog.setTitle("退出当前账号？");
        mHintDialog.setMessage("退出后将不能享受更多服务哦~");
    }

    @Override
    @OnClick({R.id.tv_about, R.id.tv_feed_back, R.id.iv_avatar, R.id.tv_nick_name,
            R.id.tv_edit_info, R.id.rl_open_vip, R.id.rl_collection, R.id.tv_clear_memory,
            R.id.tv_user_agreement, R.id.tv_logout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_open_vip:
                startActivity(new Intent(mContext, BuyVIPActivity.class));
                break;
            case R.id.rl_collection:
                startActivity(new Intent(mContext, CollectionActivity.class));
                break;
            case R.id.tv_clear_memory:
                showToast("缓存已清除");
                break;
            case R.id.tv_user_agreement:
                startActivity(new Intent(mContext, UserAgreementActivity.class));
                break;
            case R.id.tv_feed_back:
                startActivity(new Intent(mContext, FeedbackActivity.class));
                break;
            case R.id.tv_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            case R.id.tv_logout:
                mHintDialog.show();
                break;
            case R.id.iv_avatar:
            case R.id.tv_nick_name:
            case R.id.tv_edit_info:
                startActivity(new Intent(mContext, EditInfoActivity.class));
                break;
            default:
                break;
        }
    }
}
