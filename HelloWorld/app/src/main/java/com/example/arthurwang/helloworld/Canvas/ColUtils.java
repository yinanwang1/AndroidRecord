package com.example.arthurwang.helloworld.Canvas;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by arthurwang on 2021/9/8
 */
public class ColUtils {
    static public int randomRGB() {
        return Color.valueOf(new Random(System.nanoTime()).nextFloat(),
                new Random(System.nanoTime()).nextFloat(),
                new Random(System.nanoTime()).nextFloat(),
                new Random(System.nanoTime()).nextFloat()).toArgb();
    }
}
