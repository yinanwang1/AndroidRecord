package com.example.arthurwang.helloworld.Animator;

/**
 * Created by arthurwang on 2021/9/14
 */
public class Ball {
    private int color;
    private int r;
    private int x;
    private int y;

    public Ball() {
    }

    public Ball(int color, int r) {
        this.color = color;
        this.r = r;
    }

    public Ball(int color, int r, int x, int y) {
        this.color = color;
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
