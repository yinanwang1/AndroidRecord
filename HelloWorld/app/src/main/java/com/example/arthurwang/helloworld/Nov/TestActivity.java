package com.example.arthurwang.helloworld.nov;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.ui.TestButton;

public class TestActivity extends AppCompatActivity {


    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO: {
                    mCount++;

                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        mButton1.scrollTo(-scrollX, 0);

                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }


                    break;
                }

                default:
                    break;
            }

        }
    };


    private Button mButton1;
    private TestButton mButton2;
    private EditText mEditText;

    private void assignViews() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (TestButton) findViewById(R.id.button2);
        mEditText = (EditText) findViewById(R.id.editText);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        assignViews();

        mButton2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("test", "wyn touch listener");

                return false;
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "wyn click test");

                finish();

                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });






        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "button 1");
                mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
            }
        });

    }




}
