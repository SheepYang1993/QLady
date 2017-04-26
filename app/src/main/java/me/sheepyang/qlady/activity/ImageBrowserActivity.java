package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.adapter.ImageBrowserAdapter;
import me.sheepyang.qlady.fragment.ImageDetailFragment;

public class ImageBrowserActivity extends BaseActivity {
    public static final String IS_MORE_LOCK = "is_more_lock";
    public static final String IMAGE_LIST = "image_list";
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_current_page)
    TextView mTvCurrentPage;
    private boolean mIsMoreLock;
    private List<String> mImageList;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_image_browser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsMoreLock = getIntent().getBooleanExtra(IS_MORE_LOCK, false);
        mImageList = getIntent().getStringArrayListExtra(IMAGE_LIST);
        if (mImageList == null || mImageList.size() <= 0) {
            showToast("图片列表为空");
            onBackPressed();
        }
        initView();
        initListener();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvCurrentPage.setText((position + 1) + " / " + mImageList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mFragmentList.clear();
        for (String path :
                mImageList) {
            mFragmentList.add(ImageDetailFragment.newInstance(path));
        }
        mViewPager.setAdapter(new ImageBrowserAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        mTvCurrentPage.setText("1 / " + mImageList.size());
    }
}
