package me.sheepyang.qlady.activity;

import android.os.Bundle;

import me.sheepyang.qlady.R;
import me.sheepyang.qlady.fragment.ModelListFragment;

public class CollectionActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_collection;
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
}
