package com.example.arthurwang.helloworld.Animator;

import android.animation.TypeEvaluator;

/**
 * Created by arthurwang on 2021/9/14
 */
public class BallEvaluator implements TypeEvaluator<Ball> {
    @Override
    public Ball evaluate(float fraction, Ball startValue, Ball endValue) {
        Ball ball = new Ball();
        ball.setR((int) (startValue.getR() + fraction * (endValue.getR() - startValue.getR())));
        ball.setColor(evaluateColor(fraction, startValue.getColor(), endValue.getColor()));
        ball.setX((int) (startValue.getX() + fraction * (endValue.getX() - startValue.getX())));
        ball.setY(ball.getX() * ball.getX() / 800);

        return ball;
    }

    private int evaluateColor(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        float startA = ((startInt >> 24) & 0xff) / 255f;
        float startR = ((startInt >> 16) & 0xff) / 255f;
        float startG = ((startInt >> 8) & 0xff) / 255f;
        float startB = (startInt & 0xff) / 255f;

        int endInt = (Integer) endValue;
        float endA = ((endInt >> 24) & 0xff) / 255f;
        float endR = ((endInt >> 16) & 0xff) / 255f;
        float endG = ((endInt >> 8) & 0xff) / 255f;
        float endB = (endInt & 0xff) / 255f;

        startR = (float) Math.pow(startR, 2.2);
        startG = (float) Math.pow(startG, 2.2);
        startB = (float) Math.pow(startB, 2.2);

        endR = (float) Math.pow(endR, 2.2);
        endG = (float) Math.pow(endG, 2.2);
        endB = (float) Math.pow(endB, 2.2);

        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);

        a = a * 255f;
        r = (float) Math.pow(r, 1.0 / 2.2) * 255f;
        g = (float) Math.pow(g, 1.0 / 2.2) * 255f;
        b = (float) Math.pow(b, 1.0 / 2.2) * 255f;

        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }
}
