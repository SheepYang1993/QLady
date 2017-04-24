package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.adapter.ModelPhotoAdapter;
import me.sheepyang.qlady.entity.ModelEntity;

public class ModelPhotoActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    private ModelPhotoAdapter mAdapter;
    private List<ModelEntity> mData = new ArrayList<>();
    private SinaRefreshView mHeadView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_model_photo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mHeadView = new SinaRefreshView(mContext);
        mHeadView.setArrowResource(R.drawable.ico_pink_arrow);
        mRefreshLayout.setHeaderView(mHeadView);
        mRefreshLayout.setBottomView(new LoadingView(mContext));

        mAdapter = new ModelPhotoAdapter(mData);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
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
                        for (int i = 0; i < 4; i++) {
                            mData.add(new ModelEntity());
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
                            mData.add(new ModelEntity());
                        }
                        mAdapter.updata(mData);
                        mRefreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            mData.add(new ModelEntity());
        }
        mAdapter.updata(mData);
    }
}
