package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog


class KotlinActivity :  Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        KLog.e("wyn", "onConfigurationChanged, new orientation:" + newConfig.orientation)
    }
}





