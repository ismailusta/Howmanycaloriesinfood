package com.example.besinkitabi.utils

import android.content.Context
import android.content.SharedPreferences

class OzelSharedPreferences {

    companion object{
        private var sharedPreferences: SharedPreferences? = null
        private val TIME = "time"

        @Volatile
        private var instance: OzelSharedPreferences? = null

        private var lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: getOzelSharePref(context).also {
                instance = it
            }
        }
        private fun getOzelSharePref(context: Context) : OzelSharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreferences()
        }
    }
    fun saveTime(time: Long){
        sharedPreferences?.edit()?.putLong(TIME,time)?.apply()
    }
    fun getTime() = sharedPreferences?.getLong(TIME,0)
}