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

import com.example.arthurwang.helloworld.base.KLog.KLog;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by arthurwang on 2021/9/15
 */
public class FunctionView extends View {
    private Point mCoo = new Point(500, 700);
    private Picture mCooPicture;
    private Picture mGridPicture;
    private Paint mHelpPaint;
    private Paint mPaint;
    private Path mPath;

    private TreeSet<Float> Df = new TreeSet<>();
    private Map<Float, Float> funMap = new HashMap<>();
    private Paint mTextPaint;


    public FunctionView(Context context) {
        this(context, null);
    }

    public FunctionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();

        mHelpPaint = HelpDraw.getHelpPaint(Color.RED);
        mCooPicture = HelpDraw.getCoo(getContext(), mCoo);
        mGridPicture = HelpDraw.getGrid(getContext());

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);

        initDf();
        map();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        HelpDraw.draw(canvas, mGridPicture, mCooPicture);

        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);
        drawMap(canvas, funMap);
        canvas.restore();
    }

    private void initDf() {
        for (float i = 1; i <= 360; i++) {
            Df.add(i);
        }
    }

    private float f(Float thta) {
        float y = (float) (100 * (Math.tan(thta)));

        return y;
    }

    private float g(Float thta) {
        float y = (float) (200 / (Math.cos(thta)));

        return y;
    }

    private void map() {
        Df.forEach(X -> {
            float x = (float) (Math.PI / 180 * X);
            funMap.put(g(x), f(x));
        });
    }

    private void drawMap(Canvas canvas, Map<Float, Float> map) {
        map.forEach((thta, p) -> {
            KLog.e("wyn", "p is " + p + " thta is " + thta);
            KLog.e("wyn", "x is " + (float) (p * Math.cos(thta)) + " y is " + (float) (p * Math.sin(thta)));

            canvas.drawPoint(thta, p, mPaint);
        });
    }
}
