package com.example.arthurwang.helloworld.nov.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

/**
 * Created by arthurwang on 2018/11/28
 */
public class TestButton extends TextView {

    private static final String TAG = "TestButton";
    private int mScaledTouchSlop;

    private int mLastX = 0;
    private int mLastY = 0;

    public TestButton(Context context) {
        this(context, null);
    }

    public TestButton(Context context, AttributeSet attributes) {
        super(context, attributes);
        init();
    }

    public TestButton(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        Log.d(TAG, "sts: " + mScaledTouchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);

                int translationX = (int) getTranslationX() + deltaX;
                int translationY = (int) getTranslationY() + deltaY;

                setTranslationX(translationX);
                setTranslationY(translationY);



                break;
            }

            case MotionEvent.ACTION_UP: {
                break;
            }

            default:
                break;
        }

        mLastX = x;
        mLastY = y;


        return super.onTouchEvent(event);
    }

}
