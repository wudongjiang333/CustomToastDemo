package com.snaptech.customtoastdemo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by uidq0303 on 2018/1/25.
 */

public class CustomToastApplistion extends Application {
    private static CustomToastApplistion mInstance;

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runUITask(Runnable run) {
        mHandler.post(run);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public synchronized static CustomToastApplistion getInstance(){
        return mInstance;
    }
}
