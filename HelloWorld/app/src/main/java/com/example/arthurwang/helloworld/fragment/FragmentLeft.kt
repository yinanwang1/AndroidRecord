package com.example.arthurwang.helloworld.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arthurwang.helloworld.R

/**
 * Created by arthurwang on 2020/4/17
 */
class FragmentLeft: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_left, container, false)
    }
}