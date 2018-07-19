package com.vishal.baseproject.view.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.vishal.baseproject.R
import com.vishal.baseproject.databinding.ActivitySlideMenuBinding
import com.vishal.baseproject.view.baseClasses.BaseActivity


class SlidingMenuActivity: BaseActivity() {

    lateinit var binding: ActivitySlideMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_slide_menu)


    }

}