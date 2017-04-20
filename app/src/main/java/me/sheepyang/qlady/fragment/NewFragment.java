package me.sheepyang.qlady.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.adapter.NewAdapter;
import me.sheepyang.qlady.entity.NewEntity;

/**
 * 最新
 */
public class NewFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    private String mParam1;
    private NewAdapter mAdapter;
    private List<NewEntity> mData = new ArrayList<>();
    private SinaRefreshView mHeadView;
    private View mBannar;

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
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.clear();
                        for (int i = 0; i < 1; i++) {
                            mData.add(new NewEntity());
                        }
                        mAdapter.updata(mData);
                        mRefreshLayout.finishRefreshing();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            mData.add(new NewEntity());
                        }
                        mAdapter.updata(mData);
                        mRefreshLayout.finishLoadmore();
                    }
                }, 3000);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 1; i++) {
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

        mBannar = LayoutInflater.from(mContext).inflate(R.layout.layout_bannar, (ViewGroup) recyclerView.getParent(), false);
        mAdapter.addHeaderView(mBannar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
    }
}
