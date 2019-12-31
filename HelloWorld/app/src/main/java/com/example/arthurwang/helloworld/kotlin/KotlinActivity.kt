package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import com.example.arthurwang.helloworld.fragment.MySqlActivity
import kotlinx.android.synthetic.main.activity_kotlin.*


class KotlinActivity :  Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        btn_click.setOnClickListener {
            val intent = Intent(this, MySqlActivity::class.java)
            this.startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        btn_result.setOnClickListener {
            val intent = Intent(this, ScrollActivity::class.java)
            this.startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }


    }

}





