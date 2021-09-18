package com.example.arthurwang.helloworld.MidAutumn;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.Canvas.CommonPath;

import java.util.Random;

/**
 * Created by arthurwang on 2021/9/18
 */
public class StarView extends View {

    private Paint paint;
    private Path path;
    private ValueAnimator valueAnimator;
    private Random random = new Random(System.currentTimeMillis());

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        path = CommonPath.nStarPath(10, 10, 4);

        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration((int)(2000 * random.nextFloat()));
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();

                paint.setAlpha((int) (255 * value));

                invalidate();
            }
        });

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                valueAnimator.start();
            }
        }, (int)(random.nextFloat() * 50000));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
    }
}
