package me.sheepyang.qlady.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

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
    private String mParam1;
    private NewAdapter mAdapter;
    private List<NewEntity> mData = new ArrayList<>();

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
        initData();
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            mData.add(new NewEntity());
        }
    }

    private void initView() {
        mAdapter = new NewAdapter(mData);
        mAdapter.isFirstOnly(false);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
    }
}
