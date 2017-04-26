package me.sheepyang.qlady.fragment;

import android.os.Bundle;

import me.sheepyang.qlady.R;

/**
 * Created by SheepYang on 2017/4/26.
 */

public class ImageDetailFragment extends BaseFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_image_detail;
    }

    @Override
    protected void init() {

    }

    public ImageDetailFragment() {

    }

    public static ImageDetailFragment newInstance(/*String param1*/) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
}
