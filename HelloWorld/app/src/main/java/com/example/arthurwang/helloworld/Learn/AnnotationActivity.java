package com.example.arthurwang.helloworld.Learn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        try {

            Class clazz = getClassLoader().loadClass("com.example.arthurwang.helloworld.Learn.AnnotationTest");

            Field[] fields = clazz.getDeclaredFields();

            KLog.e("wyn", "field");
            for (Field field : fields) {
                MyMessage myMessage = field.getAnnotation(MyMessage.class);
                KLog.e("wyn", "name: " + myMessage.name() + " num:" + myMessage.num() + " desc:" + myMessage.desc());
            }

            Method[] methods = clazz.getMethods();

            KLog.e("wyn", "methods");
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyMessage.class)) {
                    MyMessage myMessage = method.getAnnotation(MyMessage.class);
                    KLog.e("wyn", "name: " + myMessage.name() + " num: " + myMessage.num() + " desc:" + myMessage.desc());
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
