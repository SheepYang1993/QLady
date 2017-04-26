package me.sheepyang.qlady.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.entity.ModelEntity;

/**
 * Created by SheepYang on 2017/4/22.
 */

public class ModelPhotoAdapter extends BaseQuickAdapter<ModelEntity, BaseViewHolder> {
    private LinearLayout.LayoutParams mParams;

    public ModelPhotoAdapter(List<ModelEntity> data) {
        super(R.layout.item_model_photo, data);
        mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (ScreenUtils.getScreenWidth() / 2.0));
    }

    @Override
    protected void convert(final BaseViewHolder helper, ModelEntity item) {
        helper.getView(R.id.iv_photo).setLayoutParams(mParams);
        if (item.isLock()) {
            //图片已上锁，模糊图片
            Glide.with(mContext)
                    .load(item.getImgPath())
                    .bitmapTransform(new CenterCrop(mContext), new BlurTransformation(mContext, 10, 8))
                    .into((ImageView) helper.getView(R.id.iv_photo));
        } else {
            //加载图片
            Glide.with(mContext)
                    .load(item.getImgPath())
                    .centerCrop()
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }

    public void updata(List<ModelEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
