package com.example.arthurwang.helloworld.Animator;

import android.app.Activity;
import android.os.Bundle;

import com.example.arthurwang.helloworld.SerfaceView.GLView;
import com.example.arthurwang.helloworld.base.KLog.KLog;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AnimatorActivity extends Activity {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_animator);
        setContentView(new GLView(this));

        KLog.e("wyn", simpleDateFormat.format(System.currentTimeMillis()) + "开始");
        Thread thread = new Car("Ambulance");
        Thread car = new Car("car");
        thread.start();
        car.start();

        KLog.e("wyn", simpleDateFormat.format(System.currentTimeMillis()) + "线程1执行后");
    }


    class Car extends Thread {
        public Car(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                KLog.e("wyn", simpleDateFormat.format(System.currentTimeMillis()) + getName());
                if (i == 5) {
                    yield();
                }
            }
        }
    }

}