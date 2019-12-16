package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.arthurwang.helloworld.R
import kotlinx.android.synthetic.main.activity_kotlin.*


class KotlinActivity :  Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        btn_click.setOnClickListener {
            val intent = Intent(this, ProviderActivity::class.java)
            this.startActivity(intent)
        }
    }

}




