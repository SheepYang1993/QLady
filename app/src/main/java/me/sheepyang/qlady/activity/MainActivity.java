package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.adapter.HomePageAdapter;
import me.sheepyang.qlady.entity.TabEntity;
import me.sheepyang.qlady.fragment.NewFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
    }

    private void initListener() {
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
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
        mFragmentList.add(NewFragment.newInstance("最新"));
        mFragmentList.add(NewFragment.newInstance("分类"));
        mFragmentList.add(NewFragment.newInstance("最热"));
        mTabEntities.add(new TabEntity("最新", R.drawable.ico_search, R.drawable.ico_search));
        mTabEntities.add(new TabEntity("分类", R.drawable.ico_search, R.drawable.ico_search));
        mTabEntities.add(new TabEntity("最热", R.drawable.ico_search, R.drawable.ico_search));

        viewPager.setAdapter(new HomePageAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        tabLayout.setTabData(mTabEntities);
    }
}
