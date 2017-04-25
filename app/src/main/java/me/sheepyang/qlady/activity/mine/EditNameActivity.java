package me.sheepyang.qlady.activity.mine;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.widget.QBar;

public class EditNameActivity extends BaseActivity {

    @BindView(R.id.q_bar)
    QBar mQBar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_edit_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        mQBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {

    }
}
