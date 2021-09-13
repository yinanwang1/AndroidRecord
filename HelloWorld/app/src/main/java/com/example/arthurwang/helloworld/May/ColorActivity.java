package com.example.arthurwang.helloworld.May;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

import com.example.arthurwang.helloworld.Canvas.BitmapView;
import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.base.KLog.KLog;

public class ColorActivity extends Activity {

    private ValueAnimator mAnimator;
    private BitmapView bitmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        bitmapView = findViewById(R.id.color_view);

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(100);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(animation -> {
            bitmapView.updateBall();
            bitmapView.invalidate();
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KLog.e("wyn");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                bitmapView.startAnimator();
                mAnimator.start();
                break;
        }

        return true;
    }
}