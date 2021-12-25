package com.example.challenge3.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel: ViewModel() {
    protected var compositeDisposable = CompositeDisposable()
    protected val viewModelJob = SupervisorJob()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
        viewModelJob.cancel()
    }
}