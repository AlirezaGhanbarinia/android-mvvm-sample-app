package com.aliasadi.mvvm.data.db.database

import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliasadi.mvvm.App
import com.aliasadi.mvvm.data.db.dao.LogDAO
import com.aliasadi.mvvm.data.db.entity.LogClass

@Database(entities = [LogClass::class], version = 2, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {

    @WorkerThread
    abstract fun logDao(): LogDAO

    companion object {
        val instance: LogDatabase by lazy {
            Room.databaseBuilder(App.instance.applicationContext, LogDatabase::class.java, "log-database")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    fun addLog(db: LogDatabase, log: LogClass) {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                db.logDao().insertAll(log)
            }
        }
        thread.start()
    }

    fun dropTable(db: LogDatabase) {
        db.logDao().dropTable()
    }
}