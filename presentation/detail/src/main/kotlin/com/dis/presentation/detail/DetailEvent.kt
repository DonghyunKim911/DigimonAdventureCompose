package com.dis.presentation.detail

import com.core.presentation.base.ViewEvent

interface DetailEvent: ViewEvent {

    data object NavigateBack: DetailEvent

}
