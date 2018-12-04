package com.example.arthurwang.helloworld.july;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.arthurwang.helloworld.R;

public class JNIActivity extends Activity {

    protected TextView mTvJni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);

        mTvJni = (TextView) findViewById(R.id.tv_jni);

        mTvJni.setText(new MyNdk().getString());
    }

}
