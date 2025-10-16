package com.dis.presentation.detail

import com.core.presentation.base.ViewAction

interface DetailAction: ViewAction {

    data object OnBack: DetailAction

    data object OnFavoriteClick: DetailAction

}
