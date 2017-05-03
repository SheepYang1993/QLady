package me.sheepyang.qlady.http.callback;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;

import com.lzy.okgo.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private Dialog dialog;
    private Context mContext;

    public DialogCallback(Context context) {
        this(context, true);
    }

    public DialogCallback(Context context, boolean isCancelable) {
        super();
        mContext = context;
//        dialog = DialogUtil.createLoadingDialog(context, isCancelable);
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
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
//        ExceptionUtil.handleException(mContext, response, e);
    }
}
