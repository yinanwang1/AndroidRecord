package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.blelogmanager.R;

/**
 * Created by arthurwang on 2021/9/8
 */
public class TolyProgressBar extends ProgressBar {
    private Paint mPaint;
    private int mPBWidth;
    private RectF mRectF;
    private Path mPath;
    private float[] mFloat8Left;
    private float[] mFloat8Right;

    private float mProgressX;
    private float mEndX;
    private int mTextWidth;
    private boolean mLostRight;
    private String mText;

    private int mPbBgColor = 0xffC9C9C9;
    private int mPbOnColor = 0xff54F340;
    private int mPbOnHeight = dp(6);
    private int mPbBgHeight = dp(6);
    private int mPbTxtColor = 0xff525252;
    private int mPbTxtSize = sp(10);
    private int mPbTxtOffset = sp(10);
    private boolean mPbTxtGone = false;

    public TolyProgressBar(Context context) {
        this(context, null);
    }

    public TolyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TolyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TolyProgressBar);
        mPbOnHeight = (int) a.getDimension(R.styleable.TolyProgressBar_z_pb_on_height, mPbOnHeight);
        mPbTxtOffset = (int) a.getDimension(R.styleable.TolyProgressBar_z_pb_txt_offset, mPbTxtOffset);
        mPbOnColor = a.getColor(R.styleable.TolyProgressBar_z_pb_on_color, mPbOnColor);
        mPbTxtSize = (int) a.getDimension(R.styleable.TolyProgressBar_z_pb_txt_size, mPbTxtSize);
        mPbTxtColor = a.getColor(R.styleable.TolyProgressBar_z_pb_txt_color, mPbTxtColor);
        mPbBgHeight = (int) a.getDimension(R.styleable.TolyProgressBar_z_pb_bg_height, mPbBgHeight);
        mPbBgColor = a.getColor(R.styleable.TolyProgressBar_z_pb_bg_color, mPbBgColor);
        mPbTxtGone =  a.getBoolean(R.styleable.TolyProgressBar_z_pb_txt_gone, mPbTxtGone);
        a.recycle();

        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mPbTxtSize);
        mPaint.setColor(mPbOnColor);
        mPaint.setStrokeWidth(mPbOnHeight);

        mRectF = new RectF();
        mPath = new Path();

        mFloat8Left = new float[] {
                mPbOnHeight / 2.0f, mPbOnHeight / 2.0f,
                0, 0,
                0, 0,
                mPbOnHeight / 2.0f, mPbOnHeight / 2.0f,
        };

        mFloat8Right = new float[] {
                0, 0,
                mPbOnHeight / 2.0f, mPbOnHeight / 2.0f,
                mPbOnHeight / 2.0f, mPbOnHeight / 2.0f,
                0, 0,
        };
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        mPBWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2.0f);

        parseBeforeDraw();

        if (100 == getProgress()) {
            whenOver();
        } else {
            mPbTxtGone = false;
        }

        if (mEndX > 0) {
            drawProgress(canvas);
        }

        if (!mPbTxtGone) {
            mPaint.setColor(mPbTxtColor);
            int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
            canvas.drawText(mText, mProgressX, y, mPaint);
        } else {
            mTextWidth = -mPbTxtOffset;
        }

        if (!mLostRight) {
            drawRight(canvas);
        }

        canvas.restore();

    }



    private void parseBeforeDraw() {
        mLostRight = false;
        float radio = getProgress() * 1.0f / getMax();
        mProgressX = radio * mPBWidth;
        mEndX = mProgressX - mPbTxtOffset / 2.0f;
        mText = getProgress() + "%";
        if (mProgressX + mTextWidth > mPBWidth) {
            mProgressX = mPBWidth - mTextWidth;
            mLostRight = true;
        }

        mTextWidth = (int) mPaint.measureText(mText);
    }

    private void whenOver() {
        mPbTxtGone = true;
        mFloat8Left = new float[] {
                mPbBgHeight / 2.0f, mPbBgHeight / 2.0f,
                mPbBgHeight / 2.0f, mPbBgHeight / 2.0f,
                mPbBgHeight / 2.0f, mPbBgHeight / 2.0f,
                mPbBgHeight / 2.0f, mPbBgHeight / 2.0f,
        };
    }

    private void drawProgress(Canvas canvas) {
        mPath.reset();
        mRectF.set(0, mPbOnHeight / 2.0f, mEndX, -mPbOnHeight / 2.0f);
        mPath.addRoundRect(mRectF, mFloat8Left, Path.Direction.CW);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mPbOnColor);
        canvas.drawPath(mPath, mPaint);
    }

    private void drawRight(Canvas canvas) {
        float start = mProgressX + mPbTxtOffset / 2.0f + mTextWidth;
        mPaint.setColor(mPbBgColor);
        mPaint.setStrokeWidth(mPbBgHeight);
        mPath.reset();
        mRectF.set(start, mPbBgHeight / 2f, mPBWidth, -mPbBgHeight / 2.0f);
        mPath.addRoundRect(mRectF, mFloat8Right, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
    }

    private int sp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                sp,
                getResources().getDisplayMetrics());
    }

    private int dp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics());
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mPbBgHeight, mPbOnHeight),
                    Math.abs(textHeight));

            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }

        return result;
    }
}
