package com.example.arthurwang.helloworld.nov;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.utils.LocalIntentService;
import com.socks.library.KLog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HandlerActivity extends AppCompatActivity {

    public Handler mHandler;



    private TextView mTvTextView;
    private Button mBtnClick;

    private void assignViews() {
        mTvTextView = findViewById(R.id.tv_text_view);
        mBtnClick = findViewById(R.id.btn_click);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        assignViews();

        mHandler = new MHandler();

        String hashStr = hashKeyFormUrl("http://www.baidu.com?key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&key=123&");

        KLog.e("hashStr is " + hashStr);


    }

    private String hashKeyFormUrl(String url) {
        String cacheKey;

        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }

        return cacheKey;
    }


    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            byte content = bytes[i];

            KLog.e("content is " + content);

            String hex = Integer.toHexString(0xFF & bytes[i]);

            if (1 == hex.length()) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }



    private void initListener() {
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HandlerActivity.this, LocalIntentService.class);

                intent.putExtra("task_action", "com.ryg.action.TASK1");
                startService(intent);

                intent.putExtra("task_action", "com.ryg.action.TASK2");
                startService(intent);

                intent.putExtra("task_action", "com.ryg.action.TASK3");
                startService(intent);
            }
        });


        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = "A";

                        mHandler.sendMessage(msg);
                    }
                }.start();


                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = "B";

                        mHandler.sendMessage(msg);
                    }
                }.start();


            }
        });
    }




    class MHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    mTvTextView.setText("执行了线程1的UI操作" + msg.obj);
                    break;

                case 2:
                    mTvTextView.setText("执行了线程2的UI操作" + msg.obj);
                    break;
            }
        }
    }







}
