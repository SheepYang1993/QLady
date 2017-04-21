package me.sheepyang.qlady.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.ModelDetailActivity;
import me.sheepyang.qlady.adapter.NewAdapter;
import me.sheepyang.qlady.entity.NewEntity;
import me.sheepyang.qlady.loader.GlideImageLoader;

/**
 * 最新
 */
public class NewFragment extends BaseFragment implements OnBannerListener {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    private String mParam1;
    private NewAdapter mAdapter;
    private List<NewEntity> mData = new ArrayList<>();
    private SinaRefreshView mHeadView;
    private Banner mBannar;
    private List<String> mBannarList = new ArrayList<>();

    public NewFragment() {

    }

    public static NewFragment newInstance(String param1) {
        NewFragment fragment = new NewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected void init() {
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, ModelDetailActivity.class));
            }
        });
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.clear();
                        for (int i = 0; i < 4; i++) {
                            mData.add(new NewEntity());
                        }
                        mAdapter.updata(mData);
                        mRefreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            mData.add(new NewEntity());
                        }
                        mAdapter.updata(mData);
                        mRefreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        mBannar.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> bannarList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    bannarList.add("http://www.tuigirl.com/Public/webupload/rouruan/tg_58b2f30c88086.jpg");
                }
                mBannar.update(bannarList);
            }
        }, 5000);
        for (int i = 0; i < 4; i++) {
            mData.add(new NewEntity());
        }
        mAdapter.updata(mData);
    }

    private void initView() {
        mHeadView = new SinaRefreshView(mContext);
        mHeadView.setArrowResource(R.drawable.ico_pink_arrow);
        mRefreshLayout.setHeaderView(mHeadView);
        mRefreshLayout.setBottomView(new LoadingView(mContext));

        mAdapter = new NewAdapter(mData);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mBannarList.add("http://img1.mm131.com/pic/2889/m.jpg");
        mBannarList.add("http://img1.mm131.com/pic/2889/m.jpg");
        mBannarList.add("http://img1.mm131.com/pic/2889/m.jpg");
        mBannarList.add("http://img1.mm131.com/pic/2889/m.jpg");
        mBannarList.add("http://img1.mm131.com/pic/2889/m.jpg");

        View header = LayoutInflater.from(mContext).inflate(R.layout.header_bannar, (ViewGroup) mRecyclerView.getParent(), false);
        mBannar = (Banner) header.findViewById(R.id.banner);
        mBannar.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth() / 3));
        mAdapter.addHeaderView(mBannar);
        //简单使用
        mBannar.setImages(mBannarList)
                .setDelayTime(3000)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBannar.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBannar.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        LogUtils.i("点击了bannar的第" + position + "个Item");
    }
}
