package me.sheepyang.qlady.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;

public class MineActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }

    @Override
    @OnClick({R.id.rl_open_vip, R.id.rl_collection, R.id.tv_clear_memory})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_open_vip:
                startActivity(new Intent(mContext, BuyVIPActivity.class));
                break;
            case R.id.rl_collection:
                startActivity(new Intent(mContext, CollectionActivity.class));
                break;
            case R.id.tv_clear_memory:
                showToast("缓存已清除...");
                break;
            default:
                break;
        }
    }
}
