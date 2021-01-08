package com.aliasadi.mvvm.data

import com.aliasadi.mvvm.data.db.database.LogDatabase
import com.aliasadi.mvvm.data.network.services.MovieService
import com.preference.PowerPreference
import com.preference.Preference

class DataManager {

    fun prefs(): Preference = PowerPreference.defult()
    fun logDatabase(): LogDatabase = LogDatabase.instance
    fun movieService(): MovieService = MovieService.instance

    companion object {
        val instance: DataManager by lazy {
            DataManager()
        }
    }
}