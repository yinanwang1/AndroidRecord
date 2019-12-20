package com.example.arthurwang.helloworld.kotlin;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class ViewActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private LinearLayout mLlView;
    private TextView mTvWahahha;
    private Button mBtnMove;

    private void assignViews() {
        mLlView = (LinearLayout) findViewById(R.id.ll_view);
        mTvWahahha = (TextView) findViewById(R.id.tv_wahahha);
        mBtnMove = (Button) findViewById(R.id.btn_move);
    }




    private GestureDetector gestureDetector;



    private VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        assignViews();

        mBtnMove.setEnabled(false);
        mBtnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator.ofFloat(mTvWahahha, "translationX", 0, 10,100).setDuration(100).start();

//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mTvWahahha.getLayoutParams();
//                layoutParams.width += 100;
//                layoutParams.leftMargin += 100;
//                mTvWahahha.requestLayout();

//                mTvWahahha.scrollBy(10, 10);

//                startAnimation();

                mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);

            }

        });

//        mLlView.setOnTouchListener(this);
//
//        gestureDetector = new GestureDetector(this, this);
    }

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;
    private int mCount = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO: {
                    mCount++;

                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int)(fraction * 100);
                        mBtnMove.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                }

                break;

                default:
                    break;
            }
        }
    };

    private void startAnimation() {
        final int startX = 0;
        final int deltaX = 100;


        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                mBtnMove.scrollTo(startX + (int)(deltaX * fraction), 0);
            }
        });
        animator.start();
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
