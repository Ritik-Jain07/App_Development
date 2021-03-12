package com.example.sqliteroom

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(EmployeeDataClass::class), version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun employeeDao(): RoomEmployeeDoa

}