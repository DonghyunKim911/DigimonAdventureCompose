package com.dis.presentation.detail

import com.core.presentation.base.ViewAction
import com.core.presentation.model.SkillModel

interface DetailAction: ViewAction {

    data object OnBack: DetailAction

    data object OnFavoriteClick: DetailAction

    data class OnSeeSkillAllClick(val skills: List<SkillModel?>): DetailAction

}
