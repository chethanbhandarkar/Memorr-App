package com.qualitestudios.memorr.domain.usecases

import com.qualitestudios.memorr.data.database.daoqueries.MemoryTableDao
import com.qualitestudios.memorr.data.database.tables.MemoryTable
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import javax.inject.Inject

class DeleteMemoryUseCase @Inject constructor(private val dao: MemoryTableDao) {

    suspend fun deleteMemory(memory:each_data_sealed.each_data_sealed_value)
    {
        dao.deleteMemory(MemoryTable(memory.date,memory.title,memory.message))
    }

}