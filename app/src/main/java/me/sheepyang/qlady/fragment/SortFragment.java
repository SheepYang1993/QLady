package me.sheepyang.qlady.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.EncryptUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.other.ModelListActivity;
import me.sheepyang.qlady.activity.mine.BuyVIPActivity;
import me.sheepyang.qlady.adapter.SortAdapter;
import me.sheepyang.qlady.entity.SortEntity;
import me.sheepyang.qlady.http.Api;
import me.sheepyang.qlady.util.ExceptionUtil;
import me.sheepyang.qlady.widget.dialog.QDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 分类
 */
public class SortFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    //    private String mParam1;
    private SortAdapter mAdapter;
    private List<SortEntity> mData = new ArrayList<>();
    private SinaRefreshView mHeadView;
    private QDialog mHintDialog;
    private int mCurrentPage = 1;

    public SortFragment() {

    }

    public static SortFragment newInstance(String param1) {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_refresh_rv;
    }

    @Override
    protected void init() {
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mHintDialog.setOnRightClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(mContext, BuyVIPActivity.class));
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mData.get(position).isLock()) {
                    mHintDialog.show();
                } else {
                    startActivity(new Intent(mContext, ModelListActivity.class));
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                getTypeData(1);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                getTypeData(2);
//                mRefreshLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 4; i++) {
//                            SortEntity entity = new SortEntity();
//                            entity.setPhotoNum(9987);
//                            entity.setLock(true);
//                            entity.setName("童颜巨乳");
//                            entity.setImaPath("http://img1.mm131.com/pic/2889/m.jpg");
//                            mData.add(entity);
//                        }
//                        mAdapter.updata(mData);
//                        mRefreshLayout.finishLoadmore();
//                    }
//                }, 1000);
            }
        });
    }

    private void getTypeData(int type) {
        switch (type) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
        OkGo.post(Api.GET_TYPE)
                .tag(this)
                .params("page", mCurrentPage)
                .params("rows", 5)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(String baseResponse, Call call, Response response) {

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ExceptionUtil.handleException(mContext, response, e);
                    }
                });
    }

    private void initData() {
        mData.clear();
        for (int i = 0; i < 4; i++) {
            SortEntity entity = new SortEntity();
            entity.setPhotoNum(1345);
            entity.setName("风景");
            entity.setImaPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493200839451&di=47d8a6ba814373e5da8f94d994171022&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F39%2F44%2F23358PICabP.jpg");
            mData.add(entity);
        }
        mAdapter.updata(mData);
    }

    private void initView() {

        mHeadView = new SinaRefreshView(mContext);
        mHeadView.setArrowResource(R.drawable.ico_pink_arrow);
        mRefreshLayout.setHeaderView(mHeadView);
        mRefreshLayout.setBottomView(new LoadingView(mContext));

        mAdapter = new SortAdapter(mData);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);

        mHintDialog = new QDialog(mContext);
        mHintDialog.setTitle("开通会员");
        mHintDialog.setMessage("现在开通会员，即可解锁所有照片，并可获取模特联系方式哦~");
    }
}
