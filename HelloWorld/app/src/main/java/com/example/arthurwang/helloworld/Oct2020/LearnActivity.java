package com.example.arthurwang.helloworld.Oct2020;

import android.app.Activity;
import android.os.Bundle;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class LearnActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        KLog.e("It's me");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String today = DateFormat.getDateInstance().format(new Date());
        KLog.e("today is " + today);
        long times = System.currentTimeMillis();
        KLog.e("times is " + new Date(times));
    }

}