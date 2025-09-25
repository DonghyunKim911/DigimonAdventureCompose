package com.dis.presentation.home

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.dis.domain.digimon.usecase.GetDigimonListUseCase
import com.dis.presentation.mapper.toPresentation
import com.dis.presentation.model.ContentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel2 @Inject constructor(): BaseViewModel<HomeAction, HomeEvent>() {

    // 예제 코드와 완전히 동일한 초기 상태
    private val _state = MutableStateFlow(
        HomeUiState2(items = List(20) { "Item #$it" }.toPersistentList())
    )
    val state: StateFlow<HomeUiState2> = _state.asStateFlow()

    // init 블록 제거 - 초기 데이터가 이미 있으므로 불필요

    override fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnDigimonClick -> {
                // 클릭 처리
            }
            HomeAction.OnFetchNextDigimonList -> loadMoreItems()
        }
    }

    // 예제 코드와 완전히 동일한 loadMoreItems 함수
    private fun loadMoreItems() {
        viewModelScope.launch {
            val currentState = _state.value
            Log.d("HomeViewModel", "loadMoreItems called - isLoading: ${currentState.isLoading}, items.size: ${currentState.items.size}")

            if (currentState.isLoading) {
                Log.d("HomeViewModel", "Already loading, skipping...")
                return@launch
            }

            Log.d("HomeViewModel", "Starting to load more items...")
            _state.value = _state.value.copy(isLoading = true)

            // 예제 코드와 동일한 네트워크 딜레이 시뮬레이션
            delay(1000)

            val currentItems = _state.value.items
            // 예제 코드와 동일한 새 아이템 생성
            val newItems = List(20) { "Item #${currentItems.size + it}" }

            // 예제 코드와 동일: items = items + newItems
            val updatedItems = currentItems + newItems

            _state.value = _state.value.copy(
                items = updatedItems.toPersistentList(),
                isLoading = false
            )

            Log.d("HomeViewModel", "Completed loading ${newItems.size} new items, total: ${updatedItems.size}")
        }
    }
}

@Stable
data class HomeUiState2(
    val isLoading: Boolean = false,
    val items: PersistentList<String> = persistentListOf()
)
