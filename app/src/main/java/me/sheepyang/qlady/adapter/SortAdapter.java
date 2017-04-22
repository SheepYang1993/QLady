package me.sheepyang.qlady.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sheepyang.qlady.R;
import me.sheepyang.qlady.entity.SortEntity;

/**
 * Created by Administrator on 2017/4/19.
 */

public class SortAdapter extends BaseQuickAdapter<SortEntity, BaseViewHolder> {
    private LinearLayout.LayoutParams mParams;

    public SortAdapter(List<SortEntity> data) {
        super(R.layout.item_sort, data);
        mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(ScreenUtils.getScreenWidth() / 2.5));
    }

    @Override
    protected void convert(BaseViewHolder helper, SortEntity item) {
        helper.getView(R.id.iv_photo).setLayoutParams(mParams);
        Glide.with(mContext)
                .load("http://img1.mm131.com/pic/2889/m.jpg")
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_photo));
    }

    public void updata(List<SortEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
