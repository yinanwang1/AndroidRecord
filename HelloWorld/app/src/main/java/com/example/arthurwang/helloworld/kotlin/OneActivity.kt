package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import kotlinx.android.synthetic.main.activity_one2.*

class OneActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one2)

        mBtnJumpToTwo.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            this.startActivity(intent)
        }

        mBtnPushOne.setOnClickListener {
//            val intent = Intent(this, OneActivity::class.java)
//            this.startActivity(intent)
            val intent = Intent()
            intent.setClassName(this, "com.example.arthurwang.helloworld.kotlin.OneActivity")
            this.startActivity(intent)

        }
    }
}
