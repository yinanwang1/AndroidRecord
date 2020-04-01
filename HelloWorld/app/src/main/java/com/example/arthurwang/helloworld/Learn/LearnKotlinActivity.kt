package com.example.arthurwang.helloworld.Learn

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.LinearInterpolator
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_learn_kotlin.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.sdk27.coroutines.onClick
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


//        main()
//        main1()

//        main2()

        mBtnClick.onClick {
//            mImageView.animate().translationX(500F)

//            val animator = ObjectAnimator.ofFloat(mDView, "progress", 0F, 65F)
//            animator.start()

//            mImageView.animate()
//                    .translationX(500F)
//                    .setInterpolator(LinearInterpolator())

            val animator = ObjectAnimator.ofFloat(mImageView, "translationX", 500F)
//            animator.setDuration(2000)
            animator.interpolator = AnticipateOvershootInterpolator()
            animator.start()
        }
    }

    fun main() = runBlocking {
        repeat(100_000) {
            launch {
                delay(1000L)
                printMessage(".")
            }
        }
    }

    fun main1() {
        repeat(100_100) {
            thread {
                Thread.sleep(1000L)
                printMessage(".")
            }
        }
    }

    fun main2() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        val task = Runnable {
            printMessage(".")
        }

        repeat(100_100) {
            executor.schedule(task, 1, TimeUnit.SECONDS)
        }
    }







}







