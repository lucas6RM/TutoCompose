package com.mercierlucas.tutocompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    private val _compteurLiveData = MutableLiveData(0)
    val compteurLiveData: LiveData<Int>
        get() = _compteurLiveData

    fun increment(){
        _compteurLiveData.value = compteurLiveData.value!!.plus(1)
    }
}