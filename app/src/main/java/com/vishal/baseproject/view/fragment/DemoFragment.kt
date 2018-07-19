package com.vishal.baseproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vishal.baseproject.R
import com.vishal.baseproject.view.baseClasses.BaseFragment

class DemoFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_demo, container, false)
    }

}