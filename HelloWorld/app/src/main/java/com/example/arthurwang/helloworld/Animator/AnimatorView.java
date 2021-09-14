package com.example.arthurwang.helloworld.Animator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by arthurwang on 2021/9/14
 */
public class AnimatorView extends View {
    private Paint mPaint;
    private int mRadius = 50;
    private ObjectAnimator mMoveDown;

    public AnimatorView(Context context) {
        this(context, null);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        this.setBackgroundColor(Color.YELLOW);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff94E1F7);
        mPaint.setTextSize(40);

        mMoveDown = ObjectAnimator.ofFloat(this, "translationY", 0, 300).setDuration(1000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(50, 50, 50, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMoveDown.start();
                break;
        }

        return super.onTouchEvent(event);
    }
}
