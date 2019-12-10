package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_kotlin.*


class KotlinActivity :  Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        btn_click.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            this.startActivity(intent)
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        KLog.e("wyn", "onConfigurationChanged, new orientation:" + newConfig.orientation)
    }
}





