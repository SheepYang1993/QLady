package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import me.sheepyang.qlady.widget.QBar;
import me.sheepyang.qlady.widget.dialog.QDialog;

public class ModelPhotoActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    @BindView(R.id.q_bar)
    QBar mQBar;
    private ModelPhotoAdapter mAdapter;
    private List<ModelEntity> mData = new ArrayList<>();
    private SinaRefreshView mHeadView;
    private QDialog mHintDialog;

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

        mHintDialog = new QDialog(mContext);
        mHintDialog.setTitle("开通会员");
        mHintDialog.setMessage("现在开通会员，即可解锁所有照片，并可获取模特联系方式哦~");
    }

    private void initListener() {
        mQBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHintDialog.show();
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
                        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
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
                        mData.add(new ModelEntity(true, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(true, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(true, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mData.add(new ModelEntity(true, "http://img1.mm131.com/pic/2889/m.jpg"));
                        mAdapter.updata(mData);
                        mRefreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        mData.clear();
        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
        mData.add(new ModelEntity(false, "http://img1.mm131.com/pic/2889/m.jpg"));
        mAdapter.updata(mData);
    }
}
