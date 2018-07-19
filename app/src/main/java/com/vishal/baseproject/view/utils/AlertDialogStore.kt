package com.vishal.baseproject.view.utils

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import com.fede987.statusbaralert.StatusBarAlert
import com.vishal.baseproject.R
import com.vishal.baseproject.view.baseClasses.BaseActivity
import com.vishal.baseproject.view.callback.AlertDialogListener


/***
 * Common Alert Dialog Method ...
 */
@SuppressLint("RestrictedApi")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun showAlert(baseActivity: BaseActivity, message: String, isLogout: Boolean, fragment: Fragment?, callback: AlertDialogListener) {


    val builder1 = AlertDialog.Builder(ContextThemeWrapper(baseActivity as BaseActivity,
            R.style.datepicker))
    builder1.setMessage(message)
    builder1.setCancelable(true)

    builder1.setPositiveButton(
            "Yes"
    ) { dialog, id ->
        dialog.cancel()

        if (isLogout) {
            //baseActivity!!.showProgress(baseActivity!!.getString(R.string.msg_logout))
            callback.onAlertDialogClick(true, false)
        } else {
        }
    }

    builder1.setNegativeButton("No") { dialog, id -> dialog.cancel() }

    var alertDialog = builder1.create()
    alertDialog.show()
}

