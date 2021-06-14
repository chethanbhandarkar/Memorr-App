package com.qualitestudios.memorr.data.repository

import com.qualitestudios.memorr.data.database.tables.MemoryTable
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow

interface memoryRepository {

fun getMemoriesForDate(getDate:String): Flow<List<MemoryTable>>
fun getAllMemories():Flow<List<MemoryTable>>
suspend fun insertMemory(memory:each_data_sealed.each_data_sealed_value)

}