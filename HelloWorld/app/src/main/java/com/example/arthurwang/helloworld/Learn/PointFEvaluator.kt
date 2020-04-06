package com.example.arthurwang.helloworld.Learn

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * Created by arthurwang on 2020/4/5
 */
class PointFEvaluator: TypeEvaluator<PointF> {

    private var newPoint = PointF()

    override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {
        val startValueTemp = startValue ?: PointF(0F, 0F)
        val endValueTemp = endValue ?: PointF(0F, 0F)
        val x = startValueTemp.x + (fraction * (endValueTemp.x - startValueTemp.x))
        val y = startValueTemp.y + (fraction * (endValueTemp.y - startValueTemp.y))

        newPoint.set(x, y)

        return newPoint
    }
}