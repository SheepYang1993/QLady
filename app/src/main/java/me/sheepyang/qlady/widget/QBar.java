package me.sheepyang.qlady.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.qlady.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class QBar extends RelativeLayout {
    private Context mContext;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    private View mView;

    public QBar(Context context) {
        this(context, null);
    }

    public QBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        // 加载布局
        mView = LayoutInflater.from(context).inflate(R.layout.layout_qbar, this);
        ButterKnife.bind(mView);
        init();
    }

    private void init() {
        mIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).onBackPressed();
                }
            }
        });
    }
}
