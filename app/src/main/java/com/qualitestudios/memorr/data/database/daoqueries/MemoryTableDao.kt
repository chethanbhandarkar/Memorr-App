package com.qualitestudios.memorr.data.database.daoqueries

import androidx.room.*
import com.qualitestudios.memorr.data.database.tables.MemoryTable
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(singleMemory:MemoryTable)

    @Query("SELECT * FROM MemoryTable where date like '%'||:getdate||'%'")
    fun getMemoriesForDate(getdate:String): Flow<List<MemoryTable>>

    @Delete
    suspend fun deleteMemory(singleMemory: MemoryTable)

    @Query("SELECT * FROM MemoryTable ORDER BY date DESC")
    fun getAllMemories():Flow<List<MemoryTable>>


}