package com.aliasadi.mvvm.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LogClass {

    @PrimaryKey(autoGenerate = true)
    var _id = 0

    @ColumnInfo(name = "Log")
    var log: String? = null

    @ColumnInfo(name = "Class")
    var className: String? = null

    @ColumnInfo(name = "Date")
    var date: String? = null

}