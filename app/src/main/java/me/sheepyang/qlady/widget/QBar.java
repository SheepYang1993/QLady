package me.sheepyang.qlady.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.qlady.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class QBar extends RelativeLayout {
    private String mTitle;
    private Context mContext;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QBar, defStyleAttr, 0);
        try {
            mTitle = a.getString(R.styleable.QBar_qbar_title);
        } finally {
            a.recycle();
        }
        // 加载布局
        mView = LayoutInflater.from(context).inflate(R.layout.layout_qbar, this);
        ButterKnife.bind(mView);
        init();
    }

    private void init() {
        if (!TextUtils.isEmpty(mTitle)) {
            mTvTitle.setText(mTitle);
        }
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
