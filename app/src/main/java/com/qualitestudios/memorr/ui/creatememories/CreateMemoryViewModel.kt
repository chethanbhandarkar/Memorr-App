package com.qualitestudios.memorr.ui.creatememories

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualitestudios.memorr.domain.usecases.DeleteMemoryUseCase
import com.qualitestudios.memorr.domain.usecases.InsertMemoryUseCase
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.coroutines.launch

class CreateMemoryViewModel @ViewModelInject constructor(private val insertMemoryUsecase: InsertMemoryUseCase,private val deleteMemoryUseCase:DeleteMemoryUseCase) : ViewModel() {
    // TODO: Implement the ViewModel

   fun insertMemory(singleMemory:each_data_sealed.each_data_sealed_value)=viewModelScope.launch{
insertMemoryUsecase.insertMemory(singleMemory)
    }

   fun deleteMemory(deleteMemory:each_data_sealed.each_data_sealed_value)=viewModelScope.launch {

           deleteMemoryUseCase.deleteMemory(deleteMemory)

   }


}