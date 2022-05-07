package com.study.flowpractice.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.flowpractice.common.Event
import com.study.flowpractice.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SharedFlowViewModel : ViewModel() {

    private lateinit var job: Job

    fun startRefresh(){
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true){
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }
    }

    fun stopRefresh(){
        job.cancel()
    }
}