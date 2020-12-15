package com.aliasadi.mvvm.data.db.database

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliasadi.mvvm.data.db.dao.LogDAO
import com.aliasadi.mvvm.data.db.entity.LogClass

@Database(entities = [LogClass::class], version = 2, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {
    @WorkerThread
    abstract fun logDao(): LogDAO

    companion object {
        private var sInstance: LogDatabase? = null
        private fun initialize(context: Context): LogDatabase? {
            sInstance = Room.databaseBuilder(context.applicationContext, LogDatabase::class.java, "log-database").fallbackToDestructiveMigration().build()
            return sInstance
        }

        @JvmStatic
        fun getInstance(context: Context): LogDatabase? {
            return if (sInstance == null) {
                initialize(context)
            } else {
                sInstance
            }
        }

        fun destroyInstance() {
            sInstance = null
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
}