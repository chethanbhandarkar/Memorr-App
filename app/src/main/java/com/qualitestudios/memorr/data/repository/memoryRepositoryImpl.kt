package com.qualitestudios.memorr.data.repository

import com.qualitestudios.memorr.data.database.daoqueries.MemoryTableDao
import com.qualitestudios.memorr.data.database.tables.MemoryTable
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.coroutines.flow.Flow

class memoryRepositoryImpl constructor(private val MemoryTableDao: MemoryTableDao):memoryRepository {
    override fun getMemoriesForDate(getDate:String): Flow<List<MemoryTable>> {
       return  MemoryTableDao.getMemoriesForDate(getDate)
    }

    override suspend fun insertMemory(memory: each_data_sealed.each_data_sealed_value) {

        MemoryTableDao.insertMemory(MemoryTable(memory.date,memory.title,memory.message))

    }

    override fun getAllMemories(): Flow<List<MemoryTable>> {
      return MemoryTableDao.getAllMemories()
    }


}