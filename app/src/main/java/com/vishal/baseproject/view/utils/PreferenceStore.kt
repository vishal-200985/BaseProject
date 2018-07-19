package com.vishal.baseproject.view.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.vishal.baseproject.view.baseClasses.BaseActivity


class PreferenceStore {

    var baseActivity: BaseActivity
    var appSharedPrefs: SharedPreferences? = null

    private val SESSION_PREFS: String = "app_prefs"

    companion object {
        var appPreferences: PreferenceStore? = null
        fun initAppPrefernces(baseActivity: BaseActivity): PreferenceStore {
            if (appPreferences == null) {
                return PreferenceStore(baseActivity)
            }
            return appPreferences as PreferenceStore
        }
    }

    constructor(baseActivity: BaseActivity) {
        this.baseActivity = baseActivity
    }

    /*-------------------------------------------------------------------------*/

    fun isPreferencesSet(): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseActivity)
        return sharedPreferences.all.size
    }

    fun createSharedPrefs(prefsName: String) {
        appSharedPrefs = baseActivity.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    fun clearSharedPreferences(mContext: Context, prefsName: String) {
        appSharedPrefs = mContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        appSharedPrefs!!.edit().clear().commit()
    }

    /**
     * Save String data ...
     *
     * @param key
     * @param value
     */

    fun setStringValue(key: String, value: String) {
        val editor = appSharedPrefs!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getStringValue(key: String): String {
        return appSharedPrefs!!.getString(key, "")
    }

    fun editStringValue(key: String, value: String) {
        appSharedPrefs!!.edit().putString(key, value).apply()
    }

    /**
     * Save Boolean Data ...
     *
     * @param key
     * @param value
     */
    fun setBooleanValue(key: String, value: Boolean) {
        val editor = appSharedPrefs!!.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBooleanValue(key: String): Boolean {
        return appSharedPrefs!!.getBoolean(key, false)
    }

    fun editBooleanValue(key: String, value: Boolean) {
        appSharedPrefs!!.edit().putBoolean(key, value).apply()
    }

    /**
     * Save Integer Data ...
     *
     * @param key
     * @param value
     */
    fun setIntValue(key: String, value: Int) {
        val editor = appSharedPrefs!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getIntValue(key: String): Int {
        return appSharedPrefs!!.getInt(key, -1)
    }

    fun editIntValue(key: String, value: Int) {
        appSharedPrefs!!.edit().putInt(key, value).apply()
    }

}