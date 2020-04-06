package com.example.arthurwang.helloworld.Learn

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.arthurwang.helloworld.R
import java.text.FieldPosition

/**
 * Created by arthurwang on 2020/3/27
 */
class DefineView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path = Path()
    private val text = "美丽新世界"
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.nongyu)
    private val camera = Camera()

    private var progress = 0F
    private var position = PointF(0F, 0F)

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress

        invalidate()
    }

    fun getPosition(): PointF {
        return position
    }

    fun setPosition(position: PointF) {
        this.position = position

        invalidate()
    }


    init {
        paint.textSize = 100F
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 20F

        paint1.textSize = 100F
        paint1.style = Paint.Style.FILL
        paint1.color = Color.BLACK
        paint1.strokeWidth = 5F

        path.addCircle(100F, 100F, 100F, Path.Direction.CW)






    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        paint.style = Paint.Style.FILL



        canvas?.drawArc(20F, 20F, 500F, 500F, 135F,  progress * 2.7F, false, paint)

        val text = String.format("%.0f%%", progress)
        canvas?.drawText(text, 200F, 260F, paint1)

//        canvas?.drawCircle(position.x, position.y, 50F, paint)




    }

}