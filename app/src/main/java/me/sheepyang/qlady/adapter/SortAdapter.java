package me.sheepyang.qlady.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.entity.SortEntity;

/**
 * Created by Administrator on 2017/4/19.
 */

public class SortAdapter extends BaseQuickAdapter<SortEntity, BaseViewHolder> {
    private int mScreenWidth;

    public SortAdapter(List<SortEntity> data) {
        super(R.layout.item_sort, data);
        mScreenWidth = ScreenUtils.getScreenWidth();
    }

    @Override
    protected void convert(BaseViewHolder helper, SortEntity item) {
        ViewGroup.LayoutParams lp = helper.getView(R.id.iv_photo).getLayoutParams();
        lp.width = mScreenWidth / 2;
        lp.height = (int) (lp.width * 0.75);
        helper.getView(R.id.iv_photo).setLayoutParams(lp);

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_photo_num, item.getPhotoNum() + "");
        if (item.isLock()) {
            Glide.with(mContext)
                    .load(item.getImaPath())
                    .bitmapTransform(new CenterCrop(mContext), new BlurTransformation(mContext, 10, 8))
                    .into((ImageView) helper.getView(R.id.iv_photo));
        } else {
            Glide.with(mContext)
                    .load(item.getImaPath())
                    .centerCrop()
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }

    public void updata(List<SortEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
