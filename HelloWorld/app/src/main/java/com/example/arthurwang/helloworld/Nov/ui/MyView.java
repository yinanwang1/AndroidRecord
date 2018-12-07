package com.example.arthurwang.helloworld.nov.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.arthurwang.helloworld.nov.utils.Point;
import com.example.arthurwang.helloworld.nov.utils.PointEvaluator;

/**
 * Created by arthurwang on 2018/12/7
 */
public class MyView extends View {

    public static final float RADIUS = 70f;

    private Point currentPoint;
    private Paint mPaint;


    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (null == currentPoint) {
            currentPoint = new Point(RADIUS, RADIUS);

            float x = currentPoint.getX();
            float y = currentPoint.getY();

            canvas.drawCircle(x, y, RADIUS, mPaint);

            Point startPoint = new Point(RADIUS, RADIUS);
            Point endPoint = new Point(700, 1000);

            ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
            animator.setDuration(5000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (Point) animation.getAnimatedValue();
                    invalidate();
                }
            });

            animator.start();
        } else {
            float x = currentPoint.getX();
            float y = currentPoint.getY();

            canvas.drawCircle(x, y, RADIUS, mPaint);

        }


        super.onDraw(canvas);
    }
}
