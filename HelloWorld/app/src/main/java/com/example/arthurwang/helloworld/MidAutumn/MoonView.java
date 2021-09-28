package com.example.arthurwang.helloworld.MidAutumn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.R;

/**
 * Created by arthurwang on 2021/9/18
 */
public class MoonView extends View {
    private Paint paint;
    private Bitmap moon;

    public MoonView(Context context) {
        this(context, null);
    }

    public MoonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);

        moon = BitmapFactory.decodeResource(getResources(), R.drawable.moon);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(moon, 100, 200, paint);

    }
}
