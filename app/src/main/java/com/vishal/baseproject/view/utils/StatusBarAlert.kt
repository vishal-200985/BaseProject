package com.vishal.baseproject.view.utils

import com.fede987.statusbaralert.StatusBarAlert
import com.vishal.baseproject.R
import com.vishal.baseproject.view.baseClasses.BaseActivity
import com.vishal.baseproject.view.utils.AppConstants.StatusBarAlertType.Companion.ERROR
import com.vishal.baseproject.view.utils.AppConstants.StatusBarAlertType.Companion.PROGRESS
import com.vishal.baseproject.view.utils.AppConstants.StatusBarAlertType.Companion.SUCCESS

/**
 * Function - Show alert in status bar ...
 */
fun statusBarAlertShow(baseActivity: BaseActivity, type: Int) {
    when(type){
        SUCCESS -> {StatusBarAlert.Builder(baseActivity)
                .autoHide(true)
                .showProgress(true)
                .withText("autohide!")
                .withAlertColor(R.color.white)
                .build()}

        ERROR -> {
            StatusBarAlert.Builder(baseActivity)
                    .autoHide(false)
                    .showProgress(false)
                    .withText("RED ALERT!")
                    .withAlertColor(R.color.red)
                    .build()
        }

        PROGRESS -> {
            StatusBarAlert.Builder(baseActivity)
                    .autoHide(true)
                    .withDuration(2000)
                    .showProgress(false)
                    .withText("BLINK!")
                    .withAlertColor(R.color.green)
                    .build()
        }
    }

}

fun statusBarAlertHide(baseActivity: BaseActivity) {
    StatusBarAlert.hide(baseActivity, Runnable {})
}