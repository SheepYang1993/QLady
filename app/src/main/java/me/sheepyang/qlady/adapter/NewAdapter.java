package me.sheepyang.qlady.adapter;

import android.widget.ImageView;

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
    public NewAdapter(List<NewEntity> data) {
        super(R.layout.item_new, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewEntity item) {
        Glide.with(mContext)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492624497119&di=298dc98d6977a37dab24f902d091ddd2&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1702%2F7_170228144936_2.jpg")
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.drawable.anim_loading_view)
                .into((ImageView) helper.getView(R.id.iv_avatar));
        Glide.with(mContext)
                .load("http://ww2.sinaimg.cn/mw690/006tTKPugw1f8syfu1ldgj30j60cswht.jpg")
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_desc));
    }
}
