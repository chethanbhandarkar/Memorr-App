package com.qualitestudios.memorr.di.Modules

import com.qualitestudios.memorr.data.database.daoqueries.MemoryTableDao
import com.qualitestudios.memorr.data.database.tables.MemoryTable
import com.qualitestudios.memorr.data.repository.memoryRepository
import com.qualitestudios.memorr.data.repository.memoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {



    @Provides
    fun getMemoryRepository(memoryTableDao: MemoryTableDao): memoryRepository {
        return memoryRepositoryImpl(memoryTableDao)
    }


}