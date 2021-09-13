package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.R;

/**
 * Created by arthurwang on 2021/9/9
 */
public class ColorView extends View {
    private Picture mPictureGrid;
    private Point mCoo = new Point(500, 800);
    private Picture mPictureCoo;
    private Paint mMainPaint;

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        mPictureGrid = HelpDraw.getGrid(getContext());
        mPictureCoo = HelpDraw.getCoo(getContext(), mCoo);

        mMainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMainPaint.setStyle(Paint.Style.FILL);
        mMainPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPicture(mPictureGrid);
        canvas.drawPicture(mPictureCoo);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baiyang);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        mMainPaint.setColorFilter(filter);


        mMainPaint.setStyle(Paint.Style.FILL);


        canvas.drawBitmap(bitmap, 0, 0, mMainPaint);


    }

    private Bitmap createBitmap(int color) {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        bitmapCanvas.drawRect(rect, paint);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(3);
        bitmapCanvas.drawLine(0, 0, 200, 200, paint);
        bitmapCanvas.drawLine(200, 0, 0, 200, paint);

        return bitmap;
    }

    private Bitmap createSrcBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0x882045F3);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        bitmapCanvas.drawRect(rect, paint);

        return bitmap;
    }

    private Bitmap createDstBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xff43f41D);
        bitmapCanvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, bitmap.getHeight() / 2f, paint);

        return bitmap;
    }
}
