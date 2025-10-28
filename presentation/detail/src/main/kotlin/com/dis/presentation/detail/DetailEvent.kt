package com.dis.presentation.detail

import com.core.presentation.base.ViewEvent
import com.core.presentation.model.SkillModel

interface DetailEvent : ViewEvent {
    data object NavigateBack : DetailEvent

    data class NavigateToSkillList(
        val skills: List<SkillModel?>,
    ) : DetailEvent
}
