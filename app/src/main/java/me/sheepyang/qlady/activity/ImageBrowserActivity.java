package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import me.sheepyang.qlady.R;

public class ImageBrowserActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_image_browser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
//        mViewPager.setAdapter(new ImageBrowserAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
    }
}
