package com.vishal.baseproject.view.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import android.view.ViewGroup
import android.widget.ListView




/*---------------------Date Time Functions-----------------------------------------------*/

/**
 * Convert Date from One Format to Another
 */

fun dateConversion(dateStr: String, fromFormat: String, toFormat: String): String {
    val inputFormat = SimpleDateFormat(fromFormat)
    val outputFormat = SimpleDateFormat(toFormat)

    var date: Date?
    var str: String? = null

    try {
        date = inputFormat.parse(dateStr)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return str.toString()
}

/**
 * Get Current Date ...
 */

fun getCurrentDate(toFormat: String): String {
    val c = Calendar.getInstance().getTime()
    println("Current time => $c")

    val df = SimpleDateFormat(toFormat)
    return df.format(c)
}

/**
 * Check weather first date is greater than second or not ...
 */

fun isDateAfter(fDate: String, sDate: String, dateFormat: String): Boolean {
    try {
        val df = SimpleDateFormat(dateFormat)

        var date1 = df.parse(fDate)
        var date2 = df.parse(sDate)

        return date1.after(date2)

    } catch (e: Exception) {
        return false
    }
}

/**
 * Convert Given Date Stamp to Date ...
 *
 * @param timeStamp
 * @return
 */

fun getDateFromStamp(timeStamp: Long, toFormat: String): String {

    try {
        val sdf = SimpleDateFormat(toFormat)
        val netDate = Date(timeStamp)
        return sdf.format(netDate)
    } catch (ex: Exception) {
        return ""
    }
}

/**
 * Method - Convert Time form one format to another ...
 *
 * @param time
 * @return
 */

fun convertTimeFormat(time: String, fromFormat: String, toFormat: String): String {

    var formattedTime = ""
    try {
        val sdf = SimpleDateFormat(fromFormat)
        val dateObj = sdf.parse(time)
        println(dateObj)
        println(SimpleDateFormat(toFormat).format(dateObj))
        formattedTime = SimpleDateFormat(toFormat).format(dateObj)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedTime
}

var inputParser = SimpleDateFormat(AppConstants.TIME_FORMAT, Locale.getDefault())

fun parseDate(date: String): Date {

    try {
        return inputParser.parse(date)
    } catch (e: java.text.ParseException) {
        return Date(0)
    }

}

/*---------------------Keyboard Functions-----------------------------------------------*/

/**
 * Hides the soft keyboard
 */

fun hideSoftKeyboard(mContext: Activity) {
    if (mContext.currentFocus != null) {
        val inputMethodManager = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(mContext.currentFocus!!.windowToken, 0)
    }
}

/**
 * Shows the soft keyboard
 */

fun showSoftKeyboard(mContext: Activity, view: View) {
    val inputMethodManager = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}

/*------------------------------Text Validations Functions---------------------------------*/

fun isPasswordValid(input: CharSequence): Boolean {
    val p = Pattern.compile("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$&*]).*$", Pattern.CASE_INSENSITIVE)
    val m = p.matcher(input)
    return m.matches()
}

fun isValidEmail(target: String?): Boolean {
    return if (target == null) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}

fun isTextValid(str: String): Boolean {
    return !(str.trim() == null || TextUtils.isEmpty(str))
}


/*-------------------------------Other Functions------------------------------------------*/

/**
 * finish all the activities from stack.(works only in higher versions).
 */

fun finishAll(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        (context as Activity).finishAffinity()
    } else {
        (context as Activity).finish()
    }
}

/**
 * Method checks if the app is in background or not
 * @param context
 * @return
 */


fun isAppInBackground(context: Context): Boolean {
    var isInBackground = true
    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.getPackageName()) {
                        isInBackground = false
                    }
                }
            }
        }
    } else {
        val taskInfo = am.getRunningTasks(1)
        val componentInfo = taskInfo[0].topActivity
        if (componentInfo.packageName == context.packageName) {
            isInBackground = false
        }
    }
    return isInBackground
}


/**
 * Method for setting ListView Height in ScrollView ...
 *
 * @param listView
 */
fun setListViewHeightBasedOnChildrens(listView: ListView) {
    val listAdapter = listView.getAdapter() ?: return

    val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED)
    var totalHeight = 0
    var view: View? = null
    for (i in 0 until listAdapter.getCount()) {
        view = listAdapter.getView(i, view, listView)
        if (i == 0)
            view!!.layoutParams = ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        view!!.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
        totalHeight += view.measuredHeight
    }
    val params = listView.getLayoutParams()
    params.height = totalHeight + listView.getDividerHeight() * (listAdapter.getCount() - 1)
    listView.setLayoutParams(params)
}