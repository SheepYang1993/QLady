package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.fragment.ModelListFragment;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        //必需继承FragmentActivity,嵌套fragment只需要这行代码
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, ModelListFragment.newInstance(false, true)).commitAllowingStateLoss();
    }

    @Override
    @OnClick({R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
