package com.tugceozcakir.foodbookapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SpecialSharedPreferences {
    //Singleton
    companion object{
        private var sharedPreferences: SharedPreferences? = null

        @Volatile private var instance: SpecialSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context) : SpecialSharedPreferences = instance ?: synchronized(lock) {
            instance ?: MakeSpecialSharedPreferences(context).also {
                instance = it
            }
        }
        private fun MakeSpecialSharedPreferences(context: Context) : SpecialSharedPreferences {
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPreferences()
        }
    }
    fun saveTime(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong("time", time)
        }
    }
    fun getTime() = sharedPreferences?.getLong("time", 0)
}