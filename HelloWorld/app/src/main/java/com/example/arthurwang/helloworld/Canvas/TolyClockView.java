package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.Canvas.ColUtils;
import com.example.arthurwang.helloworld.Canvas.HelpDraw;

import java.util.Calendar;

/**
 * Created by arthurwang on 2021/9/8
 */
public class TolyClockView extends View {
    private Picture mPictureGrid;
    private Point mCoo = new Point(500, 800);
    private Picture mPictureCoo;
    private Paint mMainPaint;
    private Path mMainPath;


    public TolyClockView(Context context) {
        super(context);
    }

    public TolyClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPictureGrid = HelpDraw.getGrid(getContext());
        mPictureCoo = HelpDraw.getCoo(getContext(), mCoo);

        mMainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setStrokeCap(Paint.Cap.ROUND);

        mMainPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPicture(mPictureGrid);
        canvas.drawPicture(mPictureCoo);

        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        drawBreakCircle(canvas);
        drawDot(canvas);
        drawH(canvas, hour / 12.0f * 360 - 90 + min / 60.f * 30 + sec / 3600.f * 30);
        drawM(canvas, min / 60.0f * 360 - 90 + sec / 60.0f);
        drawS(canvas, sec / 60.0f * 360 - 90);
        drawText(canvas);

        canvas.restore();
    }

    private void drawBreakCircle(Canvas canvas) {
        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.rotate(90 * i);
            mMainPaint.setStrokeWidth(8);
            mMainPaint.setColor(Color.parseColor("#D5D5D5"));
            canvas.drawArc(-350, -350, 350, 350, 10, 70, false, mMainPaint);
            canvas.restore();
        }
    }

    private void drawDot(Canvas canvas) {
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.save();
                canvas.rotate(30 * i);
                mMainPaint.setStrokeWidth(8);
                mMainPaint.setColor(ColUtils.randomRGB());
                canvas.drawLine(250, 0, 300, 0, mMainPaint);

                mMainPaint.setStrokeWidth(10);
                mMainPaint.setColor(Color.BLACK);
                canvas.drawPoint(250, 0, mMainPaint);
                canvas.restore();
            } else {
                canvas.save();
                canvas.rotate(6 * i);
                mMainPaint.setStrokeWidth(4);
                mMainPaint.setColor(Color.BLUE);
                canvas.drawLine(280, 0, 300, 0, mMainPaint);
                canvas.restore();
            }
        }
    }

    private void drawH(Canvas canvas, float degrees) {
        canvas.save();
        canvas.rotate(degrees);
        mMainPaint.setColor(Color.parseColor("#8FC552"));
        mMainPaint.setStrokeCap(Paint.Cap.ROUND);
        mMainPaint.setStrokeWidth(8);
        canvas.drawLine(0, 0, 150, 0, mMainPaint);
        canvas.restore();
    }

    private void drawM(Canvas canvas, float degree) {
        canvas.save();
        canvas.rotate(degree);
        mMainPaint.setColor(Color.parseColor("#87B953"));
        mMainPaint.setStrokeWidth(8);
        canvas.drawLine(0, 0, 200, 0, mMainPaint);
        mMainPaint.setColor(Color.GRAY);
        mMainPaint.setStrokeWidth(30);
        canvas.drawPoint(0, 0, mMainPaint);
        canvas.restore();
    }

    private void drawS(Canvas canvas, float deg) {
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setColor(Color.parseColor("#6B6B6B"));
        mMainPaint.setStrokeWidth(8);
        mMainPaint.setStrokeCap(Paint.Cap.SQUARE);

        canvas.save();
        canvas.rotate(deg);

        canvas.save();
        canvas.rotate(45);
        mMainPath.addArc(-25, -25, 25, 25, 0, 240);
        canvas.drawPath(mMainPath, mMainPaint);
        canvas.restore();

        mMainPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(-25, 0, -50, 0, mMainPaint);

        mMainPaint.setStrokeWidth(2);
        mMainPaint.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 320, 0, mMainPaint);

        mMainPaint.setStrokeWidth(15);
        mMainPaint.setColor(Color.parseColor("#8FC552"));
        canvas.drawPoint(0, 0, mMainPaint);
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        mMainPaint.setTextSize(60);
        mMainPaint.setStrokeWidth(5);
//        mMainPaint.setStyle(Paint.Style.FILL);
        mMainPaint.setTextAlign(Paint.Align.CENTER);
        mMainPaint.setColor(Color.BLUE);

        canvas.drawText("Ⅲ", 350, 30, mMainPaint);
        canvas.drawText("Ⅵ", 0, 350 + 30, mMainPaint);
        canvas.drawText("Ⅸ", -350, 30, mMainPaint);
        canvas.drawText("XII", 0, -350, mMainPaint);

        mMainPaint.setTextSize(70);
        canvas.drawText("Arthur", 0, -150, mMainPaint);
    }
}





















