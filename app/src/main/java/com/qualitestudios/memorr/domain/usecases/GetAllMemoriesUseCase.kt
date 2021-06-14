package com.qualitestudios.memorr.domain.usecases

import com.qualitestudios.memorr.data.repository.memoryRepository
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllMemoriesUseCase @Inject constructor(private val repository: memoryRepository) {


    suspend fun getAllMemories(): Flow<ArrayList<each_data_sealed.each_data_sealed_value>> {
        var allMemoryList= arrayListOf<each_data_sealed.each_data_sealed_value>()

        return flow{

            repository.getAllMemories().collect {listofmemory->

                for(memory in listofmemory)
                {
                    val date:String=memory.date
                    val title:String=memory.title
                    val message:String=memory.message
                    val memory=each_data_sealed.each_data_sealed_value(date,title,message)
                    allMemoryList.add(memory)

                }

                emit(allMemoryList)

            }


        }




    }




}