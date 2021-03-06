package me.sheepyang.qlady.http.callback;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import me.sheepyang.qlady.util.DialogUtil;
import me.sheepyang.qlady.util.ExceptionUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/8
 * 描    述：我的Github地址  https://github.com/jeasonlzy
 * 修订历史：
 * ================================================
 */
public abstract class StringDialogCallback extends StringCallback {

    private Context mContext;
    private Dialog dialog;

    public StringDialogCallback() {
        super();
    }

    public StringDialogCallback(Context context) {
        this(context, true);
    }

    public StringDialogCallback(Context context, boolean isCancelable) {
        super();
        mContext = context;
        dialog = DialogUtil.createLoadingDialog(context, isCancelable);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        ExceptionUtil.handleException(mContext, response, e);
    }
}
