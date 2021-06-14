package com.qualitestudios.memorr.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qualitestudios.memorr.domain.usecases.GetMemoryForDateUseCase

class DashboardViewModelFactory (val getMemoryForDateUseCase: GetMemoryForDateUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetMemoryForDateUseCase::class.java).newInstance(getMemoryForDateUseCase)
    }

}