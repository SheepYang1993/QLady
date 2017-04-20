package me.sheepyang.qlady.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sheepyang.qlady.R;
import me.sheepyang.qlady.entity.NewEntity;
import me.sheepyang.qlady.util.glide.GlideCircleTransform;

/**
 * Created by Administrator on 2017/4/19.
 */

public class NewAdapter extends BaseQuickAdapter<NewEntity, BaseViewHolder> {
    private int mScreenWidth;

    public NewAdapter(List<NewEntity> data) {
        super(R.layout.item_new, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewEntity item) {
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mScreenWidth, (int) (mScreenWidth/* * 1.25*/));
        helper.getView(R.id.iv_desc).setLayoutParams(params);

        Glide.with(mContext)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492624497119&di=298dc98d6977a37dab24f902d091ddd2&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1702%2F7_170228144936_2.jpg")
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.drawable.anim_loading_view)
                .into((ImageView) helper.getView(R.id.iv_avatar));
        Glide.with(mContext)
//                .load("http://ww2.sinaimg.cn/mw690/006tTKPugw1f8syfu1ldgj30j60cswht.jpg")
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492676974165&di=016cfc3b6df3f97bfc097d667e159cd7&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20150330%2Ftooopen_sy_83857437627.jpg")
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492677296016&di=1bd060483a11fa70c5ebdb3a9b648cfb&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F48%2F15%2F74w58PICvPT_1024.jpg")
                .load("http://img1.mm131.com/pic/2889/m.jpg")
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_desc));
    }

    public void updata(List<NewEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
