package me.sheepyang.qlady.activity.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.sheepyang.qlady.R;
import me.sheepyang.qlady.activity.BaseActivity;
import me.sheepyang.qlady.activity.other.ImageBrowserActivity;
import me.sheepyang.qlady.loader.GlideImageLoader;
import me.sheepyang.qlady.util.glide.GlideCircleTransform;
import me.sheepyang.qlady.widget.QBar;
import me.sheepyang.qlady.widget.dialog.SelectPhotoDialog;

import static com.lzy.imagepicker.ImagePicker.REQUEST_CODE_PREVIEW;
import static me.sheepyang.qlady.activity.other.ImageBrowserActivity.IMAGE_LIST;

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_TAKE_PICKERS = 1;
    @BindView(R.id.q_bar)
    QBar mQBar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    private SelectPhotoDialog mSelectPhotoDialog;
    private ArrayList<String> mImageList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initImagePicker();
        initListener();
    }

    private void initView() {
        mSelectPhotoDialog = new SelectPhotoDialog(mContext);
    }

    private void initListener() {
        mQBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mSelectPhotoDialog.setOnSelectClickListener(new SelectPhotoDialog.OnSelectClickListener() {
            @Override
            public void onCameraClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_TAKE_PICKERS);
            }

            @Override
            public void onAlbumClick(DialogInterface dialog, int which) {
                Intent intent1 = new Intent(mContext, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_TAKE_PICKERS);
            }

            @Override
            public void onCancelClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @Override
    @OnClick({R.id.iv_avatar, R.id.rl_edit_avatar, R.id.ll_edit_password, R.id.ll_edit_name})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit_avatar:
                mSelectPhotoDialog.show();
                break;
            case R.id.ll_edit_password:
                startActivity(new Intent(mContext, EditPasswordActivity.class));
                break;
            case R.id.ll_edit_name:
                startActivity(new Intent(mContext, EditNameActivity.class));
                break;
            case R.id.iv_avatar:
                if (mImageList != null && mImageList.size() > 0) {
                    //打开预览
                    Intent intentPreview = new Intent(this, ImageBrowserActivity.class);
                    intentPreview.putExtra(IMAGE_LIST, mImageList);
                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                } else {
                    mSelectPhotoDialog.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_TAKE_PICKERS) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    mImageList.clear();
                    for (ImageItem item : images) {
                        mImageList.add(item.path);
                    }
                    Glide.with(mContext)
                            .load(mImageList.get(0))
                            .transform(new GlideCircleTransform(mContext))
                            .placeholder(R.drawable.anim_loading_view)
                            .into(mIvAvatar);
                }
            }
        }
    }
}
