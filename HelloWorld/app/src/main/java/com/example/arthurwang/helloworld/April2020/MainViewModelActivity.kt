package com.example.arthurwang.helloworld.April2020

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.arthurwang.helloworld.R
import kotlinx.android.synthetic.main.activity_main_view_model.*

class MainViewModelActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view_model)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this, Observer {count ->
            infoText.text = count.toString()
        })

        refreshCounter()
    }

    override fun onPause() {
        super.onPause()

        sp.edit().putInt("count_reserved", viewModel.counter.value ?: 0).apply()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}
