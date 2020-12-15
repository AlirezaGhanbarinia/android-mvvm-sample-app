package com.aliasadi.mvvm.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aliasadi.mvvm.data.db.entity.LogClass

@Dao
interface LogDAO {

    @get:Query("SELECT * FROM LogClass")
    val all: List<LogClass?>?

    @Query("DELETE FROM LogClass")
    fun dropTable()

    @Insert
    fun insertAll(vararg logs: LogClass?)

    @Delete
    fun delete(log: LogClass?)
}