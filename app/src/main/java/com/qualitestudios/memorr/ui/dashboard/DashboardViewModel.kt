package com.qualitestudios.memorr.ui.dashboard

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.qualitestudios.memorr.domain.usecases.GetMemoryForDateUseCase
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(private val getMemoryForDate:GetMemoryForDateUseCase) : ViewModel() {

private val _memoryListForDate= MutableLiveData<ArrayList<each_data_sealed.each_data_sealed_value>>()
    val memoryListForDate:LiveData<ArrayList<each_data_sealed.each_data_sealed_value>>
    get()=_memoryListForDate

    private val _noofmemoriesfound=MutableLiveData<Int>()
    val noofmemoriesfound:LiveData<Int>
    get()=_noofmemoriesfound

           fun getMemoriesForDates(getDate:String)
           {

                viewModelScope.launch {
                    getMemoryForDate.getMemoriesForDate(getDate).collect {
                        _memoryListForDate.value=it;
                        _noofmemoriesfound.value=it.size


                    }
                    }
           }

}