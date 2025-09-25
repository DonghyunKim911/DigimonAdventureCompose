package com.core.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<ACTION: ViewAction, EVENT: ViewEvent>: ViewModel() {

    protected val _eventChannel = Channel<EVENT>()
    val eventChannel = _eventChannel.receiveAsFlow()

    abstract fun onAction(action: ACTION)

    override fun onCleared() {
        _eventChannel.cancel()
        super.onCleared()
    }

}
