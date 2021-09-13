package com.example.arthurwang.helloworld.Canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.arthurwang.helloworld.R;

/**
 * Created by arthurwang on 2021/8/24
 */
public class PathView extends LinearLayout {
    private int winWidth = -1;
    private int winHeight = -1;

    private Paint mRedPaint = new Paint();
    private Paint mCooPaint = new Paint();
    private Paint mPathPaint = new Paint();
    private Point winSize = new Point(1000, 1700);
    private Point coo = new Point(300, 400);
    private DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10, 5}, 0);
    private RectF rectArc = new RectF(100 + 500, 100, 500 + 500, 300);
    private Bitmap bitmap;
    private Matrix matrix = new Matrix();
    private int stars= 8;

    public void setStars(int stars) {
        this.stars = stars;

        invalidate();
    }

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attributes) {
        super(context, attributes);

        init(context, attributes);
    }

    void init(Context context, AttributeSet attributes) {


        TypedArray array = context.obtainStyledAttributes(attributes, R.styleable.PathView, 0, 0);
        if (null != array) {
            int count = array.getIndexCount();
            int index = 0;

            for (int i = 0; i < count; i++) {
                index = array.getIndex(i);
                if (index == R.styleable.PathView_win_height) {
                    winHeight = array.getDimensionPixelSize(index, -1);
                } else if (index == R.styleable.PathView_win_width) {
                    winWidth = array.getDimensionPixelSize(index, -1);
                }
            }

            array.recycle();
        }

        winSize = new Point(winWidth, winHeight);

        mRedPaint.setColor(Color.RED);
        mRedPaint.setStrokeWidth(2);
        mRedPaint.setStyle(Paint.Style.STROKE);

        mCooPaint.setColor(Color.BLACK);
        mCooPaint.setStrokeWidth(4);
        mCooPaint.setStyle(Paint.Style.STROKE);
        mCooPaint.setPathEffect(null);

        mPathPaint.setColor(Color.BLUE);
        mPathPaint.setStrokeWidth(20);
        mPathPaint.setStyle(Paint.Style.STROKE);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        matrix.setValues(new float[]{
                1, 0, 100,
                0, 0.5f, 150,
                0, 0, 1
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        winSize.x = getWidth();
        winSize.y = getHeight();

        canvas.drawColor(Color.parseColor("#E0F7F5"));

        canvas.drawPath(gridPath(50, winSize), mRedPaint);
        canvas.drawPath(cooPath(coo, winSize), mCooPaint);

        canvas.drawLine(winSize.x, coo.y, winSize.x - 40, coo.y - 20, mCooPaint);
        canvas.drawLine(winSize.x, coo.y, winSize.x - 40, coo.y + 20, mCooPaint);

        canvas.drawLine(coo.x, winSize.y, coo.x - 20, winSize.y - 40, mCooPaint);
        canvas.drawLine(coo.x, winSize.y, coo.x + 20, winSize.y - 40, mCooPaint);

        drawText4Coo(canvas, coo, winSize, mCooPaint);


        testStyle(canvas);

    }

    private void testStyle(Canvas canvas) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tiger);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPathPaint.setShader(bitmapShader);
        mPathPaint.setStyle(Paint.Style.FILL);

        canvas.drawPath(CommonPath.nStarPath(stars, 500, 250), mPathPaint);

    }

    void drawText4Coo(Canvas canvas, Point coo, Point winSize, Paint paint) {
        paint.setTextSize(50);
        canvas.drawText("x", winSize.x - 60, coo.y - 40, paint);
        canvas.drawText("y", coo.x - 40, winSize.y - 60, paint);

        paint.setTextSize(25);

        for (int i = 1; i < (winSize.x - coo.x) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x - 20 + 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x + 100 * i, coo.y, coo.x + 100 * i, coo.y - 20, paint);
        }

        for (int i = 1; i < coo.x / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x - 20 - 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x - 100 * i, coo.y, coo.x - 100 * i, coo.y - 20, paint);
        }

        // 绘制y轴的刻度
        for (int i = 1; i < (winSize.y - coo.y) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x + 25, coo.y + 5 + 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y + 100 * i, coo.x + 20, coo.y + 100 * i, paint);
        }

        for (int i = 1; i < coo.y / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x + 25, coo.y + 5 - 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y - 100 * i, coo.x + 20, coo.y - 100 * i, paint);
        }
    }

    Path gridPath(int step, Point winSize) {
        Path path = new Path();
        for (int i = 0; i < winSize.y / step + 1; i++) {
            path.moveTo(0, step * i);
            path.lineTo(winSize.x, step * i);
        }

        for (int i = 0; i < winSize.x / step + 1; i++) {
            path.moveTo(step * i, 0);
            path.lineTo(step * i, winSize.y);
        }

        return path;
    }

    Path cooPath(Point coo, Point winSize) {
        Path path = new Path();

        path.moveTo(coo.x, coo.y);
        path.lineTo(winSize.x, coo.y);

        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x - winSize.x, coo.y);

        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, coo.y - winSize.y);

        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, winSize.y);

        return path;
    }
}
