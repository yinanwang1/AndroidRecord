package com.example.arthurwang.helloworld.MidAutumn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.base.KLog.KLog;

import java.util.Random;

public class MidAutumnActivity extends Activity {
    private Random random = new Random(System.currentTimeMillis());

    private FrameLayout fl_stars;
    private LinearLayout ll_my_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_autumn);

        ll_my_test = findViewById(R.id.ll_my_test);
        fl_stars = findViewById(R.id.fl_stars);



        fl_stars.post(new Runnable() {
            @Override
            public void run() {
                drawStars();
            }
        });

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        ll_my_test.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

    }

    private void drawStars() {
        int height = fl_stars.getHeight();
        int width = fl_stars.getWidth();

        KLog.e("wyn", "width is " + height + " height is " + width);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(20, 20);

        for (int i = 0; i < 500; i++) {
            params.leftMargin = (int) (random.nextFloat() * width);
            params.topMargin = (int) (random.nextFloat() * height);

            fl_stars.addView(new StarView(MidAutumnActivity.this), params);
        }
    }
}