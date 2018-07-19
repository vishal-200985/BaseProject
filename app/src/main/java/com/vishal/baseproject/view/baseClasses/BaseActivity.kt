package com.vishal.baseproject.view.baseClasses

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vishal.baseproject.model.Toolbar
import com.vishal.baseproject.view.utils.AppConstants
import com.vishal.baseproject.view.utils.AppConstants.PreferenceKeys.Companion.SESSION_PREFS
import com.vishal.baseproject.view.utils.PreferenceStore

open class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: android.support.v7.widget.Toolbar
    var mContext: Context? = null

    lateinit var preferenceStore: PreferenceStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        initReferences()
    }

    private fun initReferences() {
        preferenceStore = PreferenceStore.initAppPrefernces(mContext as BaseActivity)

        createPreferences()
    }

    private fun createPreferences() {
        if (preferenceStore.isPreferencesSet() < 1){
            preferenceStore.createSharedPrefs(SESSION_PREFS)
        }
    }

    /**
     * Common Toolbar for All Screens ...
     */
    open fun setToolbarTitle(model: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.title = ""
    }


    /**
     * Function - Show Toast ...
     */
    fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * Function - Intent ...
     */
    fun navigateActivity(activity: Activity, data: Bundle?) {
        var intent = Intent(mContext, activity::class.java)
        if (data != null) {
            intent.putExtra(AppConstants.INTENT_DATA, data)
        }
        startActivity(intent)
    }

    /**
     * Function - Fragment Transaction ...
     */
    fun navigateFragmentReplace(id: Int, fragment: Fragment, tag: String, addToBack: Boolean) {
        when (addToBack) {
            true -> supportFragmentManager
                    .beginTransaction()
                    .replace(id, fragment, tag)
                    .addToBackStack(null)
                    .commit()

            false -> supportFragmentManager
                    .beginTransaction()
                    .replace(id, fragment, tag)
                    .commit()
        }
    }
}