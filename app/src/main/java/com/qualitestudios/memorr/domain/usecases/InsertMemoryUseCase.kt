package com.qualitestudios.memorr.domain.usecases

import com.qualitestudios.memorr.data.repository.memoryRepository
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import javax.inject.Inject

class InsertMemoryUseCase @Inject constructor(private val memoryRepository: memoryRepository)  {

    suspend fun insertMemory(singleMemory:each_data_sealed.each_data_sealed_value){
        memoryRepository.insertMemory(singleMemory)
    }




}