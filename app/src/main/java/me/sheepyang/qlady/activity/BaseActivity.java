package me.sheepyang.qlady.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import me.sheepyang.qlady.QApp;
import me.sheepyang.qlady.util.AppManager;

/**
 * Created by SheepYang on 2017/4/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public QApp mApp;
    public Context mContext;

    protected abstract
    @LayoutRes
    int setLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        mApp = (QApp) getApplication();
        mContext = this;
        ButterKnife.bind(this);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity((Activity) mContext);
    }

    public void showToast(String msg) {
        ToastUtils.showShortToast(msg);
    }
}
