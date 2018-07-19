package com.vishal.baseproject.view.activities

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.vishal.baseproject.R
import com.vishal.baseproject.view.baseClasses.BaseActivity

class SplashActivity : BaseActivity() {
    val TAG = SplashActivity::class.java.simpleName

    /**
     * Duration of wait
     */
    private val splashDisplayLength = 3000
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        setPermissions()
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    private var runnable: Runnable = Runnable {
        navigateActivity(MainActivity(), null)
    }


    /**
     * Function - To get permission for multiple permissions on runtime ...
     */

    private fun setPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        Log.e("SplasHActivity", "Result: ${report.areAllPermissionsGranted()}")
                        handler.postDelayed(runnable, splashDisplayLength.toLong())
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                        Log.e("SplasHActivity", "Result: ${permissions.size}")
                    }
                }).check()
    }
}