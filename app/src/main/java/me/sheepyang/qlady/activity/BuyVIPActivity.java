package me.sheepyang.qlady.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.sheepyang.qlady.R;

/**
 * 购买VIP会员
 */
public class BuyVIPActivity extends BaseActivity {

    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth() / 3);
        mIvPhoto.setLayoutParams(params);
        Glide.with(mContext)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493053319827&di=b010be68b11b801757de7d58c8164512&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxgs_150428%2Fdesk_005.jpg")
                .centerCrop()
                .into(mIvPhoto);
    }
}
