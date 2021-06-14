package com.qualitestudios.memorr.ui.creatememories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qualitestudios.memorr.domain.usecases.GetMemoryForDateUseCase
import com.qualitestudios.memorr.domain.usecases.InsertMemoryUseCase

class CreateMemoryViewModelFactory (val insertMemoryUseCase: InsertMemoryUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(InsertMemoryUseCase::class.java).newInstance(insertMemoryUseCase)
    }

}