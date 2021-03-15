package com.example.sqliteroom

import android.content.Context
import androidx.room.Room

object RoomDatabaseBuilder {

    private var INSTANCE: AppRoomDatabase? = null


    fun getInstance(context: Context): AppRoomDatabase {
        if (INSTANCE == null) {
            // if multiple threads are coming only 1 thread will access it at a time i.e synchronized
            synchronized(AppRoomDatabase::class)  {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    // Creating database instance  using Room
    private fun buildRoomDB(context: Context): AppRoomDatabase? {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, "emp_db")
            .fallbackToDestructiveMigration().build()
    }

}