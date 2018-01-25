package com.snaptech.customtoastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn_show_toast;
    private Button mBtn_hide_toast;
    private CustomToast mCustomToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mBtn_show_toast = (Button) findViewById(R.id.btn_show_toast);
        mBtn_hide_toast = (Button) findViewById(R.id.btn_hide_toast);
    }

    private void initData() {
        mCustomToast = new CustomToast(this);
    }

    private void initListener() {
        mBtn_show_toast.setOnClickListener(this);
        mBtn_hide_toast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_toast:
                showToast();
                break;
            case R.id.btn_hide_toast:
                hideToast();
            default:
                break;
        }
    }

    private void showToast() {
        if (mCustomToast != null) {
            mCustomToast.alwaysShow("This is a test message !!! ");
        }
    }

    private void hideToast() {
        if (mCustomToast != null) {
            mCustomToast.hide();
        }
    }
}
