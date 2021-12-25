package com.example.challenge3.base.rx

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class MySingleObserver<T>(private val compositeDisposable: CompositeDisposable?) :
    SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable?.add(d)
    }
}