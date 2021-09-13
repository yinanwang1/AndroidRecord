package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by arthurwang on 2021/9/13
 */
public class SimpleCubicView extends View {
    private Point mCoo = new Point(50, 500);
    private Picture mCooPicture;
    private Picture mGridPicture;
    private Paint mHelpPaint;
    private Paint mPaint;
    private Path mPath;

    Point p0 = new Point(0, 0);
    Point p1 = new Point(200, 200);
    Point p2 = new Point(300, -100);
    Point p3 = new Point(400, 300);

    public SimpleCubicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleCubicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();

        mHelpPaint = new Paint();
        mHelpPaint.setColor(Color.RED);
        mHelpPaint.setStyle(Paint.Style.FILL);

        mCooPicture = HelpDraw.getCoo(getContext(), mCoo);
        mGridPicture = HelpDraw.getGrid(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPicture(mGridPicture);
        canvas.drawPicture(mCooPicture);

        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);

        mPath.moveTo(p0.x, p0.y);
        mPath.cubicTo(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        mPath.cubicTo(p3.x * 2 - p2.x, p2.y, p3.x * 2 - p1.x, p1.y, p3.x * 2 - p0.x, p0.y);
        canvas.drawPath(mPath, mPaint);

        mHelpPaint.setStrokeWidth(10);
        canvas.drawCircle(p0.x, p0.y, 5, mHelpPaint);
        canvas.drawCircle(p1.x, p1.y, 5, mHelpPaint);
        canvas.drawCircle(p2.x, p2.y, 5, mHelpPaint);
        canvas.drawCircle(p3.x, p3.y, 5, mHelpPaint);

        mHelpPaint.setStrokeWidth(2);
        mHelpPaint.setStyle(Paint.Style.STROKE);
        mHelpPaint.setColor(Color.YELLOW);
        canvas.drawLine(p0.x, p0.y, p1.x, p1.y, mHelpPaint);
        canvas.drawLine(p2.x, p2.y, p3.x, p3.y, mHelpPaint);

        canvas.restore();

    }

    public static boolean judgeCircleArea(Point src, Point dst, float r) {
        return disPos2d(src.x, src.y, dst.x, dst.y) <= r;
    }

    public static float disPos2d(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    Point src = new Point(0,0);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                src.x = (int) event.getX() - mCoo.x;
                src.y = (int) event.getY() - mCoo.y;

                break;
            case MotionEvent.ACTION_MOVE:
                if (judgeCircleArea(src, p0, 30)) {
                    setPos(event, p0);
                }

                if (judgeCircleArea(src, p1, 30)) {
                    setPos(event, p1);
                }

                if (judgeCircleArea(src, p2, 30)) {
                    setPos(event, p2);
                }

                if (judgeCircleArea(src, p3, 30)) {
                    setPos(event, p3);
                }

                mPath.reset();

                src.x = (int) event.getX() - mCoo.x;
                src.y = (int) event.getY() - mCoo.y;

                invalidate();

                break;
        }

        return true;
    }

    private void setPos(MotionEvent event, Point p) {
        p.x = (int) event.getX() - mCoo.x;
        p.y = (int) event.getY() - mCoo.y;
    }
}
