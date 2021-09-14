package com.example.arthurwang.helloworld.Animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.Canvas.ColUtils;

/**
 * Created by arthurwang on 2021/9/14
 */
public class AnimatorSetView extends View {
    private static final String TAG = "AnimatorView";
    private Paint mPaint;
    private int mRadius = 50;
    private int mColor = 50;
    private ObjectAnimator colorAnimator;
    private ObjectAnimator radiusAnimator;
    ObjectAnimator translationX;
    ObjectAnimator alpha;
    private AnimatorSet mSet;


    public AnimatorSetView(Context context) {
        this(context, null);
    }

    public AnimatorSetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff94e1f7);
        mSet = new AnimatorSet();

        translationX = ObjectAnimator
                .ofFloat(this, "translationX", 0, 300, 150, 100, 20 , 100)
                .setDuration(3000);
        alpha = ObjectAnimator
                .ofFloat(this, "alpha", 1, 0.5f, 1, 0, 1)
                .setDuration(3000);
        radiusAnimator = ObjectAnimator
                .ofInt(this, "Radius", 50, 100, 50, 100, 20, 100)
                .setDuration(3000);
        colorAnimator = ObjectAnimator
                .ofInt(this, "color", 0xff0000ff, 0xfff2ba38, 0xffdd70bc)
                .setDuration(3000);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        mSet.playSequentially(translationX, alpha, radiusAnimator, colorAnimator);

        translationX.setRepeatMode(ValueAnimator.REVERSE);
        translationX.setRepeatCount(ValueAnimator.INFINITE);
        translationX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setColor(Color.BLUE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setRadius(50);

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                setColor(ColUtils.randomRGB());
            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                translationX.start();

                break;
        }

        return super.onTouchEvent(event);
    }

    public void setRadius(int radius) {
        mRadius = radius;
        setMeasuredDimension(mRadius * 2, mRadius * 2);

        invalidate();
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(color);

        invalidate();
    }
}

















