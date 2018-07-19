package com.vishal.baseproject.view.utils

class AppConstants {

    companion object {
        const val DATE_FORMAT = "dd-MMM-yyy"
        const val TIME_FORMAT = "HH:mm"

        const val INTENT_DATA = "intent_data"
    }

    class PreferenceKeys{
        companion object {
            const val SESSION_PREFS = "session"
        }
    }

    class StatusBarAlertType {
        companion object {
            const val ERROR = 0
            const val SUCCESS = 1
            const val PROGRESS = 2
        }
    }


}