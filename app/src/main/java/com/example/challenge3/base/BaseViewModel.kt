package com.example.challenge3.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel: ViewModel() {
    protected val viewModelJob = SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}