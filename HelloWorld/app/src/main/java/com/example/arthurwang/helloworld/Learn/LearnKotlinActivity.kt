package com.example.arthurwang.helloworld.Learn

import android.animation.*
import android.app.Activity
import android.graphics.PointF
import android.os.Bundle
import android.view.animation.*
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_learn_kotlin.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.nio.file.Path
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.random.Random

class LearnKotlinActivity : Activity() {


    fun printMessage(message: String) {
        KLog.e("wyn", message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_kotlin)

        mBtnReset.onClick {
//            val holder1 = PropertyValuesHolder.ofFloat("ScaleX", 1F)
//            val holder2 = PropertyValuesHolder.ofFloat("ScaleY", 1F)
//            val holder3 = PropertyValuesHolder.ofFloat("alpha", 1F)
//
//            val animator = ObjectAnimator.ofPropertyValuesHolder(mImageView, holder1, holder2, holder3)
//            animator.interpolator = LinearInterpolator()
//
//            val animator1 = ObjectAnimator.ofFloat(mImageView, "translationX", 0F)
//            animator1.interpolator = DecelerateInterpolator()
//
//            val animatorSet = AnimatorSet()
//            animatorSet.playSequentially(animator, animator1)
//
//            animatorSet.start()
        }

        mBtnClick.onClick {
//            val holder1 = PropertyValuesHolder.ofFloat("ScaleX", 0.1F)
//            val holder2 = PropertyValuesHolder.ofFloat("ScaleY", 0.1F)
//            val holder3 = PropertyValuesHolder.ofFloat("alpha", 0.1F)
//
//            val animator = ObjectAnimator.ofPropertyValuesHolder(mImageView, holder1, holder2, holder3)
//            animator.interpolator = LinearInterpolator()
//
//            val animator1 = ObjectAnimator.ofFloat(mImageView, "translationX", 500F)
//            animator1.interpolator = DecelerateInterpolator()
//
//            val animatorSet = AnimatorSet()
//            animatorSet.playSequentially(animator, animator1)
//
//            animatorSet.start()


            val keyframe1 = Keyframe.ofFloat(0F, 0F)
            val keyframe2 = Keyframe.ofFloat(0.5F, 100F)
            val keyframe3 = Keyframe.ofFloat(1F, 80F)
            val holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3)

            val animator = ObjectAnimator.ofPropertyValuesHolder(mDView, holder)
            animator.duration = 5000
            animator.start()
        }
    }







}







