package com.example.arthurwang.helloworld.kotlin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.socks.library.KLog;

/**
 * Created by arthurwang on 2019-12-18
 */
public class ListViewEx extends ListView {
    private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;

    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setmHorizontalScrollViewEx2(HorizontalScrollViewEx2 horizontalScrollViewEx2) {
        mHorizontalScrollViewEx2 = horizontalScrollViewEx2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                KLog.e("wyn", "dx: " + deltaX + " dy: " + deltaY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }

                break;
            }

            case MotionEvent.ACTION_UP: {
                break;
            }

            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        return super.dispatchTouchEvent(ev);
    }
}
