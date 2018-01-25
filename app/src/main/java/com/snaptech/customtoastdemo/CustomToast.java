package com.snaptech.customtoastdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by uidq0303 on 2018/1/25.
 */

public class CustomToast {
    private static final String TAG = "CustomToast";
    private Context mContext;
    private Toast mToast;
    private final int ALWAYS_SHOW = 0;
    private boolean mIsNeedHide = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ALWAYS_SHOW:
                    if (mToast != null) {
                        if (!mIsNeedHide) {
                            Log.d(TAG,"toast show");
                            mToast.show();
                            mHandler.sendEmptyMessageDelayed(ALWAYS_SHOW,10);
                        } else {
                            Log.d(TAG,"toast hide");
                            mToast.cancel();
                            mHandler.removeMessages(ALWAYS_SHOW);
                            mIsNeedHide = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public CustomToast(Context context){
        mContext = context;
    }

    public void alwaysShow(final String text){
        //防止在子线程中弹Toast导致应用Crash
        CustomToastApplistion.getInstance().runUITask(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(CustomToastApplistion.getInstance(), text, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(text);
                }
                mHandler.sendEmptyMessageDelayed(ALWAYS_SHOW,10);
            }
        });
    }

    /**
     * 隐藏Toast框
     */
    public void hide(){
        this.mIsNeedHide = true;
    }
}
