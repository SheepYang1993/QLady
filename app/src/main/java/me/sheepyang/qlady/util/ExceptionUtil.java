package me.sheepyang.qlady.util;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.exception.OkGoException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Response;

/**
 * Created by SheepYang on 2017/2/21.
 */

public class ExceptionUtil {
    public static String handleException(Context context, Response response, Exception e) {
        String msg = "";
        if (null != response) {
            int code = response.code();
            switch (code) {
                case 404:
                    msg = "找不到服务器地址";
                    break;
                case 408:
                    msg = "请求链接超时";
                    break;
                default:
                    msg = "服务器异常";
                    break;
            }
        } else {
            if (e instanceof SocketTimeoutException) {
                msg = "服务器响应超时";
            } else if (e instanceof ConnectException) {
                msg = "服务器请求超时！";
            } else if (e instanceof SocketException) {
                msg = "Socket异常！";
            } else if (e instanceof OkGoException) {
                msg = "服务器数据异常！";
            } else {
                msg = "出现异常了！请联系客服人员！";
            }
        }
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShortToast(msg);
        }
        return msg;
    }
}
