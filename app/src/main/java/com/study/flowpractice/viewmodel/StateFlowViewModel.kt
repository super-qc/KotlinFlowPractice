package com.study.flowpractice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class StateFlowViewModel(app: Application) : AndroidViewModel(app) {

    val number = MutableStateFlow<Int>(0);

    fun plusNumber(){
        number.value++
    }
    fun minusNumber(){
        number.value--
    }

}