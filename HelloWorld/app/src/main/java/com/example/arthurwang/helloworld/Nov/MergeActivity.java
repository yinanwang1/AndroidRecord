package com.example.arthurwang.helloworld.nov;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class MergeActivity extends AppCompatActivity {





    private LinearLayout mInflatedStart;
    private ViewStub mStub;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;

    private void assignViews() {
        mInflatedStart = (LinearLayout) findViewById(R.id.inflatedStart);
        mStub = (ViewStub) findViewById(R.id.stub);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);


        assignViews();

//        mStub.setOnInflateListener(inflateListener);
//        mBtn1.setOnClickListener(clickListener);
//        mBtn2.setOnClickListener(clickListener);
//        mBtn3.setOnClickListener(clickListener);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testANR();
//            }
//        }).start();
//
//        SystemClock.sleep(10);
//
//        initView();

        List<Double> test = new ArrayList<>();
        test.add(2.0);
        test.add(2.0);test.add(2.0);test.add(5.0);test.add(2.0);test.add(2.0);test.add(2.0);

        List<Double> copy = new ArrayList<>();
        copy.addAll(test);

        for (Double value : copy) {
            KLog.e("value is " + value);

            if (5.0 == value) {
                test.remove(0);
            }
        }



    }

    private synchronized void testANR() {
        SystemClock.sleep(30 * 1000);
    }

    private synchronized void initView() {

    }

    private ViewStub.OnInflateListener inflateListener = new ViewStub.OnInflateListener() {
        @Override
        public void onInflate(ViewStub stub, View inflated) {
            Toast.makeText(MergeActivity.this, "ViweStub is loaded", Toast.LENGTH_SHORT).show();
        }
    };


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                {
                    try {
                        LinearLayout linearLayout = (LinearLayout) mStub.inflate();
                        RatingBar bar = linearLayout.findViewById(R.id.ratingBar1);
                        bar.setNumStars(4);
                    } catch (Exception e) {
                        e.printStackTrace();

                        mStub.setVisibility(View.VISIBLE);
                    }
                }
                    break;

                case R.id.btn2:
                {
                    mStub.setVisibility(View.GONE);
                }
                break;

                case R.id.btn3:
                {
                    LinearLayout linearLayout = findViewById(R.id.inflatedStart);
                    RatingBar ratingBar = linearLayout.findViewById(R.id.ratingBar1);
                    float numStart = ratingBar.getRating();
                    numStart++;

                    if (numStart > 4) {
                        numStart = 0;
                    }

                    ratingBar.setRating(numStart);
                }
                break;
            }
        }
    };




}
