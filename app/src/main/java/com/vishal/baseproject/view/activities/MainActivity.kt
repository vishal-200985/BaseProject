package com.vishal.baseproject.view.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.WindowManager
import com.vishal.baseproject.R
import com.vishal.baseproject.databinding.ActivityMainBinding
import com.vishal.baseproject.model.Toolbar
import com.vishal.baseproject.view.baseClasses.BaseActivity
import com.vishal.baseproject.view.fragment.DemoFragment

class MainActivity : BaseActivity() {

    var TAG = MainActivity::class.java.simpleName

    lateinit var binding: ActivityMainBinding
    private var toolbarModel: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Use this code if you want to not get capture screenshot or screen recording of your activity ...
        //for security purpose ...
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()



        //navigateFragmentReplace(R.id.auth_container, DemoFragment(), "", true)
        //statusBarAlertShow(this, AppConstants.StatusBarAlertType.PROGRESS)
    }

    override fun setToolbarTitle(model: Toolbar) {
        super.setToolbarTitle(model)

        this.toolbarModel = model
        binding.toolbar!!.model = toolbarModel
    }
}
