package com.example.challenge3.adapter

class Pager {
    var index = 0
    var isLoading = false
    var isListFinished = false

    fun isLoadingAllowed(): Boolean {
        return !isLoading && !isListFinished
    }
}