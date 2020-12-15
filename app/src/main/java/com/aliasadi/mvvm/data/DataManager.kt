package com.aliasadi.mvvm.data

import com.aliasadi.mvvm.App
import com.aliasadi.mvvm.data.db.database.LogDatabase
import com.aliasadi.mvvm.data.db.database.LogDatabase.Companion.getInstance
import com.aliasadi.mvvm.data.network.services.MovieService
import com.preference.PowerPreference
import com.preference.Preference

class DataManager {
    val prefs: Preference
        get() = PowerPreference.defult()
    val logDatabase: LogDatabase?
        get() = getInstance(App.getInstance())
    val movieService: MovieService
        get() = MovieService.getInstance()

    companion object {
        private var sInstance: DataManager? = null

        @get:Synchronized
        val instance: DataManager?
            get() {
                if (sInstance == null) {
                    sInstance = DataManager()
                }
                return sInstance
            }
    }
}