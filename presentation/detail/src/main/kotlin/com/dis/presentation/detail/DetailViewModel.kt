package com.dis.presentation.detail

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.presentation.mapper.toPresentation
import com.dis.core.domain.model.Favorite
import com.dis.domain.digimon.usecase.DeleteDigimonFavoriteUseCase
import com.dis.domain.digimon.usecase.GetDigimonDetailUseCase
import com.dis.domain.digimon.usecase.SaveDigimonFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    private val getDigimonDetailUseCase: GetDigimonDetailUseCase,
    private val saveDigimonFavoriteUseCase: SaveDigimonFavoriteUseCase,
    private val deleteDigimonFavoriteUseCase: DeleteDigimonFavoriteUseCase,
    @Assisted private val digimonId: Int,
): BaseViewModel<DetailAction, DetailEvent>() {

    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val ceh = CoroutineExceptionHandler { context, throwable ->
        _uiState.update {
            it.copy(
                isLoading = false,
                isError = true,
            )
        }
    }

    init {
        fetchDigimonDetail()
    }

    override fun onAction(action: DetailAction) {
        when (action) {
            DetailAction.OnBack -> _eventChannel.trySend(DetailEvent.NavigateBack)
            DetailAction.OnFavoriteClick -> onFavoriteClick()
            is DetailAction.OnSeeSkillAllClick -> _eventChannel.trySend(DetailEvent.NavigateToSkillList(action.skills))
        }
    }

    private fun fetchDigimonDetail() = viewModelScope.launch(ceh) {
        val digimon = getDigimonDetailUseCase.invoke(digimonId)
        _uiState.update {
            it.copy(
                digimon = digimon?.toPresentation(),
                isLoading = false,
            )
        }
    }

    private fun onFavoriteClick() = viewModelScope.launch(ceh) {
        val favorite = Favorite(digimonId)
        _uiState.value.digimon?.let { digimon ->
            if (digimon.isFavorite) {
                deleteDigimonFavoriteUseCase.invoke(favorite)
            } else {
                saveDigimonFavoriteUseCase.invoke(favorite)
            }
            _uiState.update {
                it.copy(
                    digimon = it.digimon?.copy(
                        isFavorite = !it.digimon.isFavorite
                    ),
                )
            }
        }
    }

    @AssistedFactory
    fun interface Factory {
        fun create(digimonId: Int): DetailViewModel
    }

}
