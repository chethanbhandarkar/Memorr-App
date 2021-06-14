package com.qualitestudios.memorr.ui.importantmemories

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qualitestudios.memorr.domain.usecases.GetAllMemoriesUseCase
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ImportantMemoriesViewModel @ViewModelInject constructor(private val usecase:GetAllMemoriesUseCase): ViewModel() {


    private val _allmemories=MutableLiveData<ArrayList<each_data_sealed.each_data_sealed_value>>()
    val allmemories:LiveData<ArrayList<each_data_sealed.each_data_sealed_value>>
    get()=_allmemories
    fun getAllMemories()=viewModelScope.launch {
        usecase.getAllMemories().collect {
_allmemories.value=it

        }
    }



}