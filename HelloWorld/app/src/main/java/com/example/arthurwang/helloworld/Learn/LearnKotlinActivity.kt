package com.example.arthurwang.helloworld.Learn

import android.app.Activity
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog
import kotlin.random.Random

class LearnKotlinActivity : Activity() {


    fun printMessage(message: String) {
        KLog.e("wyn", message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_kotlin)


        val number = Random.nextInt(100)

        val evenOrNull = number.takeIf { it % 2 == 0 }
        val oddOrNull = number.takeUnless { it % 2 == 0 }
        println("even: $evenOrNull, odd: $oddOrNull")


    }







}







