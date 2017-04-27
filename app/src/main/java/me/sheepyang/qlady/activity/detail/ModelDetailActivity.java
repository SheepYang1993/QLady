package me.sheepyang.qlady.activity.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.activity.mine.BuyVIPActivity;
import me.sheepyang.qlady.fragment.ModelListFragment;
import me.sheepyang.qlady.util.glide.GlideCircleTransform;
import me.sheepyang.qlady.widget.dialog.QDialog;

public class ModelDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    private QDialog mHintialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_model_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mHintialog.setOnRightClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(mContext, BuyVIPActivity.class));
            }
        });
    }

    private void initData() {
        Glide.with(mContext)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492624497119&di=298dc98d6977a37dab24f902d091ddd2&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1702%2F7_170228144936_2.jpg")
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.drawable.anim_loading_view)
                .into(mIvAvatar);
    }

    private void initView() {
        mHintialog = new QDialog(mContext);
        mHintialog.setTitle("获取联系方式");
        mHintialog.setMessage("开通会员才能查看我的联系方式哦~");
        //必需继承FragmentActivity,嵌套fragment只需要这行代码
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, ModelListFragment.newInstance(false, false)).commitAllowingStateLoss();
    }

    @Override
    @OnClick({R.id.tv_get_contact})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_contact:
                if (mApp.isLogin()) {
                    mHintialog.show();
                } else {
                    mApp.toLogin(mContext);
                }
                break;
            default:
                break;
        }
    }
}
