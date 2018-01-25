文章地址：[http://blog.csdn.net/wudongjiang333/article/details/79167059](http://blog.csdn.net/wudongjiang333/article/details/79167059)

##Android 动态控制Toast的显示与隐藏
我们知道，Toast信息会在显示一段时间后自动隐藏，因为它有一个Toast队列，系统会依次从这个队列中取出一个Toast。<br/>


----------


在最近的开发中，我**遇到一个问题，在设置某项功能的过程中，显示Toast框信息，直到设置成功后，才能隐藏该Toast框！**<br/>
而Toast只提供了**Toast.LENGTH_SHORT**和**Toast.LENGTH_LONG**两个选项，那我们该怎么做呢？<br/>
查看Toast提供的方法发现**cancel()**用于隐藏当前的Toast，结合定时器，很容易实现我想要的功能！

##关键代码：

```
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
```

##效果图:
![效果图](http://img.blog.csdn.net/20180125213220295?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd3Vkb25namlhbmczMzM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##完整Demo地址
[代码地址](https://github.com/wudongjiang333/CustomToastDemo)
