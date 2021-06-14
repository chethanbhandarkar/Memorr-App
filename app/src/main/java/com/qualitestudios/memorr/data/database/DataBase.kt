package com.qualitestudios.memorr.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qualitestudios.memorr.data.database.daoqueries.MemoryTableDao
import com.qualitestudios.memorr.data.database.tables.MemoryTable

@Database(entities = [MemoryTable::class],version = 1,exportSchema = false)
abstract class MainDataBase:RoomDatabase() {



    abstract fun memoryTableDao(): MemoryTableDao


    companion object{
        private var INSTANCE: MainDataBase? = null
        fun getInstance(context: Context): MainDataBase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        MainDataBase::class.java,
                        "roomdb")
                        .build()
            }

            return INSTANCE as MainDataBase
        }
    }
}