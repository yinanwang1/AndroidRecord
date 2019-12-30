package com.example.arthurwang.helloworld.Art;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class WindowActivity extends Activity {

    private Button mBtnShowWindow;
    private Button mBtnBack;

    private ThreadLocal<Boolean> mBooleanTrhreadLocal = new ThreadLocal<>();

    private void assignViews() {
        mBtnShowWindow = (Button) findViewById(R.id.btn_show_window);
        mBtnBack = (Button) findViewById(R.id.btn_back);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        assignViews();

        mBtnShowWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("666");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(getApplicationContext())) {
                        KLog.e("777");

                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, 100);
                    } else {
                        KLog.e("888");

                        addView();
                    }
                }
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WindowActivity.this.finish();

//                Dialog dialog = new Dialog(WindowActivity.this);
//                TextView textView = new TextView(WindowActivity.this);
//                textView.setText("This is toast!");
//                dialog.setContentView(textView);
//
//                dialog.show();

                threadLocal();

            }
        });
    }

    private void threadLocal() {
        mBooleanTrhreadLocal.set(true);

        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanTrhreadLocal.set(false);

                KLog.e("wyn", "[Thread#1] mBooleanTrhreadLocal = " + mBooleanTrhreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                KLog.e("wyn", "[Thread#2] mBooleanTrhreadLocal = " + mBooleanTrhreadLocal.get());
            }
        }.start();

        KLog.e("wyn", "[MainThread] mBooleanTrhreadLocal = " + mBooleanTrhreadLocal.get());

    }


    private void addView() {
        KLog.e("444");
        if (Build.VERSION_CODES.M <= Build.VERSION.SDK_INT) {
            if (!Settings.canDrawOverlays(this)) {
                return;
            }
        }

        KLog.e("555");

        Button mFloatingButton = new Button(this);
        mFloatingButton.setText("button");
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("Hello, My lady!!!");
            }
        });
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.gravity = Gravity.CENTER;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
//        mLayoutParams.x = 100;
//        mLayoutParams.y = 300;
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (null != windowManager) {
            windowManager.addView(mFloatingButton, mLayoutParams);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        KLog.e("111");

        if (100 == requestCode) {
            KLog.e("222");
            if (Build.VERSION_CODES.M <= Build.VERSION.SDK_INT) {
                if (Settings.canDrawOverlays(this)) {
                    KLog.e("333");

                    addView();
                }
            }
        }
    }
}
