package com.example.arthurwang.helloworld.kotlin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.socks.library.KLog;

public class ScrollerActivity extends Activity {

    private LinearLayout lay1, lay2, lay0;
    private Scroller mScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scroller);

        mScroller = new Scroller(this, new DecelerateInterpolator(2.0f));
        lay1 = new MyLineareLayout(this);
        lay2 = new MyLineareLayout(this);

        lay1.setBackgroundColor(this.getResources().getColor(android.R.color.darker_gray));
        lay2.setBackgroundColor(this.getResources().getColor(android.R.color.white));
        lay0 = new ContentLinearLayout(this);
        lay0.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        this.setContentView(lay0, p0);

        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        p1.weight = 1;
        lay0.addView(lay1, p1);

        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        p2.weight = 1;
        lay0.addView(lay2, p2);

        MyButton btn1 = new MyButton(this);
        MyButton btn2 = new MyButton(this);
        btn1.setText("btn in layout1");
        btn2.setText("btn in layout2");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(0, 0, -200, 0, 500);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(0, 0, -300, 0, 600);
            }
        });

        lay1.addView(btn1);
        lay2.addView(btn2);

        lay0.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        lay1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        lay2.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    class MyButton extends Button {
        public MyButton(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            KLog.e("wyn", "MyButton " + this.toString() + " onDraw...");
        }
    }

    class MyLineareLayout extends LinearLayout {
        public MyLineareLayout(Context context) {
            super(context);
        }

        @Override
        public void computeScroll() {
            KLog.e("wyn", "MyLineareLayout " + this.toString() + " computeScroll...");

            if (mScroller.computeScrollOffset()) {
                scrollTo(mScroller.getCurrX(), 0);

                KLog.e("wyn", "getCurrX = " + mScroller.getCurrX());
            }

            super.computeScroll();
        }
    }

    class ContentLinearLayout extends LinearLayout {
        public ContentLinearLayout(Context context) {
            super(context);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            KLog.e("wyn", "ContentLinearLayout dispatchDraw");

            super.dispatchDraw(canvas);
        }
    }
}
