package me.sheepyang.qlady.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import me.sheepyang.qlady.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class QBar extends RelativeLayout {
    public QBar(Context context) {
        this(context, null);
    }

    public QBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.layout_qbar, this);
        init();
    }

    private void init() {
    }
}
