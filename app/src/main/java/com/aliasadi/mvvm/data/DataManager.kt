package com.aliasadi.mvvm.data

import com.aliasadi.mvvm.App
import com.aliasadi.mvvm.data.db.database.LogDatabase
import com.aliasadi.mvvm.data.network.services.MovieService
import com.preference.PowerPreference
import com.preference.Preference

class DataManager {

    fun prefs(): Preference = PowerPreference.defult()
    fun logDatabase(): LogDatabase = LogDatabase.getInstance(App.instance)
    fun movieService(): MovieService = MovieService.getInstance()

    companion object {

        @Volatile
        private var instance: DataManager? = null

        fun getInstance(): DataManager {
            if (instance == null) {
                synchronized(DataManager::class.java) {
                    if (instance == null) {
                        instance = DataManager()
                    }
                }
            }
            return instance!!
        }
    }
}