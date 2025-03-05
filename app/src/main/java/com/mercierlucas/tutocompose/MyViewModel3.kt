package com.mercierlucas.tutocompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel3 : ViewModel() {
    private val _compteurStateFlow = MutableStateFlow(0)
    val compteurStateFlow = _compteurStateFlow.asStateFlow()

    private val _goToOherScreenSharedFlow = MutableSharedFlow<Boolean>()
    val goToOtherScreenSF = _goToOherScreenSharedFlow.asSharedFlow()

    fun increment(){
        _compteurStateFlow.value = compteurStateFlow.value.plus(1)
        if(compteurStateFlow.value > 5)
            viewModelScope.launch {
                _goToOherScreenSharedFlow.emit(true) // emit() est suspend
            }
    }
}