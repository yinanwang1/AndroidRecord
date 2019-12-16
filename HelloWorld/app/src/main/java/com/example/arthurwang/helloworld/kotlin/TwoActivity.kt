package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        mBtnPushOne.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            this.startActivity(intent)
        }
    }
}