package com.example.arthurwang.helloworld.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class ViewActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private LinearLayout mLlView;
    private GestureDetector gestureDetector;



    private VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mLlView = (LinearLayout) findViewById(R.id.ll_view);

        mLlView.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        KLog.e("wyn", "111");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        KLog.e("wyn", "222");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        KLog.e("wyn", "333");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        KLog.e("wyn", "444");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        KLog.e("wyn", "555");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        KLog.e("wyn", "666");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        KLog.e("wyn", "777");

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        KLog.e("wyn", "888");

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        KLog.e("wyn", "999");

        return true;
    }
}
