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
import com.example.arthurwang.helloworld.base.KLog.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arthurwang on 2021/9/18
 */
public class MoonView extends View {
    private Paint paint;
    private Bitmap moon;
    private int x = 0;
    private int y = 100;
    private int a = 5;
    private Disposable disposable;

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

        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        x += 10;
                        y = (8 * a * a * a) / (x * x + 4 * a * a) + 100;

                        invalidate();

                        KLog.e("wyn", "111");

                    }
                });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(moon, x, y, paint);

        if (x > getWidth()) {
            disposable.dispose();
        }
    }
}
