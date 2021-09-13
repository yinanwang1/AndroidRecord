package com.example.arthurwang.helloworld.Canvas;

import androidx.annotation.NonNull;

/**
 * Created by arthurwang on 2021/9/10
 */
public class Ball implements Cloneable {
    public float aX;
    public float aY;
    public float vX;
    public float vY;
    public float x;
    public float y;
    public int color;
    public float r;
    public long born;

    @NonNull
    public Ball clone() {
        Ball clone = null;
        try {
            clone = (Ball) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        assert clone != null;
        return clone;
    }
}
