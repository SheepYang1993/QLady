package me.sheepyang.qlady.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.sheepyang.qlady.R;

/**
 * Created by SheepYang on 2017/4/26.
 */

public class ImageDetailFragment extends BaseFragment {
    private static final String IMAGE_PATH = "image_path";
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    private String mPath;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_image_detail;
    }

    @Override
    protected void init() {
        initData();
    }

    private void initData() {
        Glide.with(mContext)
                .load(mPath)
                .centerCrop()
                .into(mIvPhoto);
    }

    public ImageDetailFragment() {

    }

    public static ImageDetailFragment newInstance(String path) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_PATH, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPath = getArguments().getString(IMAGE_PATH);
        }
    }
}
