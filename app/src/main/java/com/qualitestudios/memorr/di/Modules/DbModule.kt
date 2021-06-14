package com.qualitestudios.memorr.di.Modules

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import com.qualitestudios.memorr.data.database.MainDataBase
import com.qualitestudios.memorr.data.database.daoqueries.MemoryTableDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//here we provide database and dao

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): MainDataBase =MainDataBase.getInstance(context)

       // Room.databaseBuilder(context,MainDataBase::class.java,"roomdb").build()


    @Provides
    fun providesMemoryTableDao(database: MainDataBase):MemoryTableDao=database.memoryTableDao()

}