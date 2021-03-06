package me.sheepyang.qlady;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import java.util.logging.Level;

import me.sheepyang.qlady.activity.login.LoginActivity;
import me.sheepyang.qlady.util.AppManager;


/**
 * Created by SheepYang on 2017/4/19.
 */

public class QApp extends Application {
    public static final int REQUEST_LOGIN = 1;
    private boolean mLogin;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initOkGo();
    }

    private void initOkGo() {
        OkGo.init(this);
        OkGo.getInstance()
                // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                .debug(QContacts.TAG, BuildConfig.IS_DEBUG ? Level.INFO : Level.OFF, BuildConfig.IS_DEBUG ? true : false)
                //cookie持久化存储，如果cookie不过期，则一直有效
                .setCookieStore(new PersistentCookieStore());
    }

    public boolean isLogin() {
        return mLogin;
    }

    public void setLogin(boolean login) {
        this.mLogin = login;
    }

    public static void exitApp(Context context) {
        AppManager.getAppManager().AppExit(context);
    }

    public void toLogin(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(new Intent(context, LoginActivity.class), REQUEST_LOGIN);
        }
    }

    public void logout() {
        setLogin(false);
    }
}
