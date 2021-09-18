package com.example.arthurwang.helloworld.Animator;

import static com.example.arthurwang.helloworld.Canvas.Utils.dp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by arthurwang on 2021/9/14
 */
public class AbilityView extends View {
    private float mRadius = dp(getContext(), 100);
    private float mLineWidth = dp(getContext(), 1);

    private Paint mLinePaint;
    private Paint mFillPaint;
    private Paint mTextPaint;
    private Paint mAbilityPaint;

    private Path mPath;
    private Path mAbilityPath = new Path();

    private String[] mAbilityInfo = new String[]{"破坏力", "速度", "射程距离", "持久力", "精密度", "成长性"};
    private int[] mAbilityMark = new int[]{100, 40, 60, 100, 80, 100};
    private String[] mMarkMapper = new String[]{"A", "B", "C", "D", "E"};


    public AbilityView(Context context) {
        this(context, null);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillPaint.setStrokeWidth(0.0f * mRadius);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mAbilityPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAbilityPaint.setColor(0x8897c5fe);


        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mRadius, mRadius);
        drawOutCircle(canvas);
        drawInnerCircle(canvas);
        drawInfoText(canvas);
        drawAbility(canvas);
    }

    private void drawOutCircle(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(0, 0, mRadius, mLinePaint);
        float r2 = mRadius - 0.08f * mRadius;
        canvas.drawCircle(0, 0, r2, mLinePaint);

        for (int i = 0; i < 22; i++) {
            canvas.save();
            canvas.rotate(360 / 22f * i);
            canvas.drawLine(0, -mRadius, 0, -r2, mFillPaint);
            canvas.restore();
        }

        canvas.restore();
    }

    private void drawInnerCircle(Canvas canvas) {
        canvas.save();
        float innerRadius = 0.6f * mRadius;
        canvas.drawCircle(0, 0, innerRadius, mLinePaint);
        canvas.save();
        for (int i = 0; i < 6; i++) {
            canvas.save();
            canvas.rotate(60 * i);
            mPath.moveTo(0, -innerRadius);
            mPath.rLineTo(0, innerRadius);
            for (int j = 0; j < 6; j++) {
                mPath.moveTo(-mRadius * 0.02f, innerRadius / 6 * j);
                mPath.rLineTo(mRadius * 0.02f * 2, 0);
            }
            canvas.drawPath(mPath, mLinePaint);
            canvas.restore();
        }

        canvas.restore();
    }

    private void drawInfoText(Canvas canvas) {
        float r2 = mRadius - 0.08f * mRadius;
        float innerRadius = 0.6f * mRadius;
        for (int i = 0; i < 6; i++) {
            canvas.save();
            canvas.rotate(60 * i + 180);
            mTextPaint.setTextSize(mRadius * 0.1f);
            canvas.drawText(mAbilityInfo[i], 0, r2 - 0.06f * mRadius, mTextPaint);
            mTextPaint.setTextSize(mRadius * 0.15f);
            canvas.drawText(abilityMark2Str(mAbilityMark[i]), 0, r2 - 0.18f * mRadius, mTextPaint);
            canvas.restore();
        }
        mTextPaint.setTextSize(mRadius * 0.07f);
        for (int k = 0; k < 5; k++) {
            canvas.drawText(mMarkMapper[k],
                    mRadius * 0.06f,
                    innerRadius / 6 * (k + 1) + mRadius * 0.02f - innerRadius,
                    mTextPaint);
        }
    }

    private String abilityMark2Str(int mark) {
        if (mark <= 100 && mark > 80) {
            return mMarkMapper[0];
        } else if (mark <= 80 && mark > 60) {
            return mMarkMapper[1];
        } else if (mark <= 60 && mark > 40) {
            return mMarkMapper[2];
        } else if (mark <= 40 && mark > 20) {
            return mMarkMapper[3];
        } else if (mark <= 20 && mark > 0) {
            return mMarkMapper[4];
        }

        return "*";
    }

    private void drawAbility(Canvas canvas) {
        float step = 0.6f * mRadius / 6;
        mAbilityPath.moveTo(0, -mAbilityMark[0] / 20f * step);
        for (int i = 1; i < 6; i++) {
            float mark = mAbilityMark[i] / 20f;
            mAbilityPath.lineTo(
                    (float) (mark * step * Math.cos(Math.PI / 180 * (-30 + 60 * (i - 1)))),
                    (float) (mark * step * Math.sin(Math.PI / 180 * (-30 + 60 * (i - 1))))
            );
        }

        mAbilityPath.close();
        canvas.drawPath(mAbilityPath, mAbilityPaint);
    }
}





