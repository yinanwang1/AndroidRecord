package com.example.arthurwang.helloworld.April2020

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by arthurwang on 2020/5/21
 */
class MainViewModel(countReserved: Int): ViewModel() {
    val counter = MutableLiveData<Int>()

    init {
        counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }


}