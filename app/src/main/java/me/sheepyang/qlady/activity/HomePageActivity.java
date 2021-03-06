package me.sheepyang.qlady.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.QApp;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.mine.MineActivity;
import me.sheepyang.qlady.activity.other.SearchActivity;
import me.sheepyang.qlady.adapter.HomePageAdapter;
import me.sheepyang.qlady.entity.TabEntity;
import me.sheepyang.qlady.fragment.ModelListFragment;
import me.sheepyang.qlady.fragment.SortFragment;

public class HomePageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private long mCurrentTime;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
    }

    private void initListener() {
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        mTitleList.add("最新");
        mTitleList.add("分类");
        mTitleList.add("最热");
        mFragmentList.add(ModelListFragment.newInstance(true, true));
        mFragmentList.add(SortFragment.newInstance("分类"));
        mFragmentList.add(ModelListFragment.newInstance(false, true));
        mTabEntities.add(new TabEntity("最新", R.drawable.ico_search, R.drawable.ico_search));
        mTabEntities.add(new TabEntity("分类", R.drawable.ico_search, R.drawable.ico_search));
        mTabEntities.add(new TabEntity("最热", R.drawable.ico_search, R.drawable.ico_search));

        mViewPager.setAdapter(new HomePageAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        mTabLayout.setTabData(mTabEntities);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mCurrentTime < 2000) {
            mCurrentTime = 0;
            QApp.exitApp(mContext);
        } else {
            mCurrentTime = System.currentTimeMillis();
            showToast("再次点击退出APP");
        }
    }

    @Override
    @OnClick({R.id.iv_search, R.id.iv_mine})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mine:
                if (mApp.isLogin()) {
                    startActivity(new Intent(mContext, MineActivity.class));
                } else {
                    mApp.toLogin(mContext);
                }
                break;
            case R.id.iv_search:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            default:
                break;
        }
    }
}
