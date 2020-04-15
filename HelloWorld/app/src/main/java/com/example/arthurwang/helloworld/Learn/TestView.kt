package com.example.arthurwang.helloworld.Learn

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog

/**
 * Created by arthurwang on 2020/4/15
 */
class TestView: View {

    constructor(context: Context): super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TestView)
        val text = ta.getString(R.styleable.TestView_text)
        val textAttr = ta.getInteger(R.styleable.TestView_testAttr, -1)
        KLog.e("wyn", "text = $text, textAttr = $textAttr")

        ta.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        val measureSpec = MeasureSpec.makeMeasureSpec(specSize, specMode)

        KLog.e("wyn", "measureSpec is $measureSpec, specMode is $specMode, specSize is $specSize")
    }

}