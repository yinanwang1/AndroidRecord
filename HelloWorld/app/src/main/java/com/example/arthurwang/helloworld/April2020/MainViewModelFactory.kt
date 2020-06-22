package com.example.arthurwang.helloworld.April2020

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by arthurwang on 2020/5/21
 */
class MainViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}