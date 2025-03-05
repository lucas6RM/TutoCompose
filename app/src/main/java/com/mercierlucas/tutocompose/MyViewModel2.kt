package com.mercierlucas.tutocompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel2 : ViewModel() {
    private val _compteurStateFlow = MutableStateFlow(0)
    val compteurStateFlow = _compteurStateFlow.asStateFlow()

    fun increment(){
        _compteurStateFlow.value = compteurStateFlow.value.plus(1)
    }
}