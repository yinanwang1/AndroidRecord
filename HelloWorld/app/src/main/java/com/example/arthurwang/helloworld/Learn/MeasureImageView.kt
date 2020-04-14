package com.example.arthurwang.helloworld.Learn

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.socks.library.KLog

/**
 * Created by arthurwang on 2020/4/10
 */
class MeasureImageView(context: Context, attributeSet: AttributeSet): AppCompatImageView(context, attributeSet) {



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        KLog.e("wyn", "widthMeasureSpec is $widthMeasureSpec")
//        KLog.e("wyn", "heightMeasureSpec is $heightMeasureSpec")
//
//
//        setMeasuredDimension(View.getDefaultSize(0, widthMeasureSpec), View.getDefaultSize(0, heightMeasureSpec))
//
//        val childWidthSize = measuredWidth
//        val widthTemp = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY)
//        val heightTemp = widthTemp
//
//        KLog.e("wyn", "heightTemp is $heightTemp")
//        KLog.e("wyn", "heightTemp is $heightTemp")

        super.onMeasure(widthMeasureSpec, widthMeasureSpec)

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)


    }
}