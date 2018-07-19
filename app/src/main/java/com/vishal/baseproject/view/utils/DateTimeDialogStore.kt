package com.vishal.baseproject.view.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.TextView
import com.vishal.baseproject.R
import com.vishal.baseproject.view.baseClasses.BaseActivity
import com.vishal.baseproject.view.callback.DialogCallback
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date Picker method ...
 *
 * @param text_view
 * @param edtText
 */
private var datePickerDialog: DatePickerDialog? = null

fun datePicker(baseActivity: BaseActivity, text_view: TextView?, isDob: Boolean, callback: DialogCallback) {
    val date = arrayOf("")
    val c = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)


    datePickerDialog = DatePickerDialog(baseActivity, R.style.datepicker,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                val temp_month = monthOfYear + 1
                val temp_year = year

                date[0] = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                Log.e("dateIs", "dateIs: " + date[0])

                if (!isDob) {
                    datePickerDialog!!.getDatePicker().minDate = System.currentTimeMillis() - 1000
                }

                val c = Calendar.getInstance()
                System.out.println("Current time => " + c.time)

                val df = SimpleDateFormat("dd-MM-yyyy")
                val currentDate = df.format(c.time)

                // convert date to time stamp ...
                var date_s: Date? = null
                var date_selected: Date? = null
                try {
                    date_s = df.parse(currentDate) as Date
                    date_selected = df.parse(date[0]) as Date
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                System.out.println("Today is " + date_s!!.time)
                val l_diff = date_selected!!.time - date_s.time

                if (!isDob) {
                    //condition ...
                    if (l_diff > -1) {

                        val currentYear = c.get(Calendar.YEAR)
                        val currentMonth = c.get(Calendar.MONTH)

                        Log.e("datePicker", "selected_date : " + date[0])

                        if (temp_year === currentYear) {
                            if (temp_month > currentMonth) {
                                if (temp_month - currentMonth < 2) {
                                    if (text_view != null) {
                                        text_view.text = date[0]
                                    } /*else if (edtText != null) {
                                        edtText.text = date[0]
                                    }*/
                                    callback.onDialogListener(date[0])
                                } else {
                                    baseActivity!!.showToast("Invalid")
                                }
                            } else if (temp_month < currentMonth) {
                                if (currentMonth - temp_month < 2) {
                                    if (text_view != null) {
                                        text_view.text = date[0]
                                    } /*else if (edtText != null) {
                                        edtText.text = date[0]
                                    }*/
                                    callback.onDialogListener(date[0])
                                } else {
                                    baseActivity!!.showToast("Invalid")
                                }
                            }
                        } else if (temp_year > currentYear) {
                            if (temp_month < 2) {
                                if (text_view != null) {
                                    text_view.text = date[0]
                                } /*else if (edtText != null) {
                                    edtText.text = date[0]
                                }*/
                                callback.onDialogListener(date[0])
                            } else {
                                baseActivity!!.showToast("Invalid")
                            }
                        }


                    } else {
                        baseActivity!!.showToast("Selected Date cannot be less than current date.")
                    }
                } else {
                    if (!date_selected.after(date_s)) {
                        if (text_view != null) {
                            text_view.text = date[0]
                        } /*else if (edtText != null) {
                            edtText.text = date[0]
                        }*/
                        callback.onDialogListener(date[0])
                    } else {
                        baseActivity!!.showToast("Date of birth cannot be future date.")
                    }
                }

            }, mYear, mMonth, mDay)

    datePickerDialog!!.show()
}

/**
 * Time Picker Method ...
 *
 * @param text_view
 * @param edtText
 * @return
 */
fun timePicker(baseActivity: BaseActivity, text_view: TextView?, date: String, startTime: String?) {
    val time = arrayOf("")
    val mcurrentTime = Calendar.getInstance()
    val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
    val minute = mcurrentTime.get(Calendar.MINUTE)
    val mTimePicker: TimePickerDialog
    mTimePicker = TimePickerDialog(baseActivity, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
        time[0] = selectedHour.toString() + ":" + selectedMinute

        Log.e("timePicker", "time : " + time[0])
        if (!isDateAfter(date, getCurrentDate(AppConstants.DATE_FORMAT), AppConstants.DATE_FORMAT)) {

            val sdf = SimpleDateFormat("HH:mm")
            val currentDateandTime = sdf.format(Date())
            val str = parseDate(time[0]).after(parseDate(currentDateandTime))

            if (str) {
                if (startTime == null || startTime == "") {
                    if (text_view != null) {
                        text_view.text = time[0]
                    } //else edtText?.setText(time[0])
                } else {
                    val str_time = parseDate(time[0]).after(parseDate(startTime))

                    if (str_time) {
                        if (text_view != null) {
                            text_view.text = time[0]
                        } //else edtText?.setText(time[0])
                    } else {
                        baseActivity.showToast("Selected Time cannot be less than start time.")
                    }
                }
            } else {
                baseActivity.showToast("Selected Time cannot be less than current time.")
            }
        } else {
            val sdf = SimpleDateFormat("HH:mm")
            val currentDateandTime = sdf.format(Date())
            val str = parseDate(time[0]).after(parseDate(currentDateandTime))
            if (isDateAfter(date, getCurrentDate(AppConstants.DATE_FORMAT), AppConstants.DATE_FORMAT)) {
                if (startTime == null || startTime == "") {
                    if (text_view != null) {
                        text_view.text = time[0]
                    } //else edtText?.setText(time[0])
                } else {

                    val str_time = parseDate(time[0]).after(parseDate(startTime))
                    if (str_time) {
                        if (text_view != null) {
                            text_view.text = time[0]
                        } //else edtText?.setText(time[0])
                    } else {
                        baseActivity.showToast("Selected Time cannot be less than start time.")
                    }
                }
            } else {
                if (str) {
                    Log.e("MYTIME", "sdfsd : " + startTime!!)
                    if (startTime == null || startTime == "") {
                        if (text_view != null) {
                            text_view.text = time[0]
                        } //else edtText?.setText(time[0])
                    } else {

                        val str_time = parseDate(time[0]).after(parseDate(startTime))
                        if (str_time) {
                            if (text_view != null) {
                                text_view.text = time[0]
                            } //else edtText?.setText(time[0])
                        } else {
                            baseActivity.showToast("Selected Time cannot be less than start time.")
                        }
                    }
                } else {
                    baseActivity.showToast("Selected Time cannot be less than current time.")
                }
            }
        }
    }, hour, minute, false)//Yes 24 hour time
    mTimePicker.setTitle("Select Time")
    mTimePicker.show()

}