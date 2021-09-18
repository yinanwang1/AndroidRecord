package com.example.arthurwang.helloworld.MidAutumn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by arthurwang on 2021/9/18
 */
public class BackgroundView extends View {

    private Paint paint = new Paint();

    public BackgroundView(Context context) {
        this(context, null);
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int colorStart = Color.parseColor("#4855B6");
        int color1 = Color.parseColor("#5A63A9");
        int color2 = Color.parseColor("#747897");
        int color3 = Color.parseColor("#A87285");
        int colorEND = Color.parseColor("#CE5480");

        LinearGradient gradient = new LinearGradient(
                0,
                0,
                getWidth(),
                getHeight(),
                new int[]{colorStart, color1, color2, color2, color3, colorEND},
                null,
                Shader.TileMode.CLAMP);

        paint.setShader(gradient);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
}
