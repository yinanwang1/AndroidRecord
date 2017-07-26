package com.example.arthurwang.helloworld.July;

/**
 * Created by arthurwang on 2017/7/26.
 */

public class MyNdk {
    static {
        System.loadLibrary("MyLibrary");
    }

    public native String getString();
}
