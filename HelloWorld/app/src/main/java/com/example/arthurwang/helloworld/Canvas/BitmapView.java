package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.base.KLog.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurwang on 2021/9/10
 */
public class BitmapView extends View {
    private int d = 4;
    private List<Ball> mBalls = new ArrayList<>();
    private Point mCoo = new Point(500, 800);
    private Picture mPictureCoo;
    private Picture mPictureGrid;
    private Paint mPaint;
    private Bitmap mBitmap;
    private float mRunTime;

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mPictureGrid = HelpDraw.getGrid(getContext());
        mPictureCoo = HelpDraw.getCoo(getContext(), mCoo);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color);
        initBall(mBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPicture(mPictureGrid);
//        canvas.drawPicture(mPictureCoo);

        for (int i = 0; i < mBalls.size(); i++) {
            canvas.save();
            Ball ball = mBalls.get(i);
            canvas.translate(ball.x, ball.y);
            mPaint.setColor(ball.color);
            canvas.drawPath(CommonPath.nStarPath(5, d, d / 2f), mPaint);
            canvas.restore();
        }
    }

    private void initBall(Bitmap bitmap) {
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.x = i * d + d / 2f;
                ball.y = j * d + d / 2f;
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangeInt(-15, 35);
                ball.aY = 0.98f;
                ball.born = System.currentTimeMillis();
                ball.color = bitmap.getPixel(i, j);

                KLog.e("wyn ball.vX " + ball.vX);
                KLog.e("wyn ball.vY " + ball.vY);

                mBalls.add(ball);
            }
        }
    }

    public void updateBall() {
        KLog.e("wyn 111");
        for (int i = 0; i < mBalls.size(); i++) {
            Ball ball = mBalls.get(i);
            if (System.currentTimeMillis() - mRunTime > 2000) {
                mBalls.remove(i);
            }
            ball.x += ball.vX;
            ball.y += ball.vY;
            ball.vY += ball.aY;
            ball.vX += ball.aX;
        }
    }

    private float rangeInt(int start, int end) {
        return (float) (Math.random() * (start + end) - start);
    }

    private int blackAndWhite(int a, int r, int g , int b) {
        int grey = (int) (r * 0.3 + g * 0.59 + b * 0.11);
        if (grey > 255 / 2) {
            grey = 255;
        } else {
            grey = 0;
        }

        return Color.argb(a, grey, grey, grey);
    }

    private int grey(int a, int r, int g, int b) {
        int grey = (int) (r * 0.3 + g * 0.59 + b * 0.11);

        return Color.argb(a, grey, grey, grey);
    }

    private int reverse(int a, int r, int g, int b) {
        return Color.argb(a, 255 - r , 255 - g, 255 - b);
    }

    public void startAnimator() {
        mRunTime = System.currentTimeMillis();
    }
}
