package com.example.arthurwang.helloworld.April2020;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class LearnStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_string);

        StringBuilder stringBuffer = new StringBuilder(100);
        stringBuffer.append("Runoob..");
        KLog.e("wyn", "string is " + stringBuffer);
        stringBuffer.append("Runoob..");
        KLog.e("wyn", "string is " + stringBuffer);
        stringBuffer.append("Runoob..");
        KLog.e("wyn", "string is " + stringBuffer);

        stringBuffer.insert(8, "掺入");
        KLog.e("wyn", "string is " + stringBuffer);


    }



}