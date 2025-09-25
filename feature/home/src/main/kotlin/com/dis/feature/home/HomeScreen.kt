package com.dis.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dis.core.ui.designsystem.component.DigimonItem
import com.dis.core.ui.designsystem.theme.Background
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme
import com.dis.core.ui.util.ObserveAsEvents
import com.dis.presentation.home.HomeAction
import com.dis.presentation.home.HomeEvent
import com.dis.presentation.home.HomeUiState
import com.dis.presentation.home.HomeViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreenRoot(
    navigateToDigimonDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is HomeEvent.NavigateToDigimonDetail -> {
                navigateToDigimonDetail(event.id)
            }
        }

    }

    HomeScreen(
        state = state,
        lazyGridState = lazyGridState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeUiState,
    lazyGridState: LazyGridState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
    threshold: Int = 8,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        TopAppBar(
            title = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Digimon Adventure",
                        color = Color.Black,
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                    )
                }
            }
        )

        Box {
            val digimons = state.digimonList

            val shouldLoadMore = remember(
                lazyGridState,
                state.digimonList.size,
            ) {
                derivedStateOf {
                    val totalItemsCount = lazyGridState.layoutInfo.totalItemsCount
                    val lastVisibleItemIndex = lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    lastVisibleItemIndex >= (totalItemsCount - threshold) &&
                            !state.isLoading &&
                            !state.isLastPageReached &&
                            digimons.isNotEmpty()
                }
            }

            LaunchedEffect(shouldLoadMore) {
                snapshotFlow { shouldLoadMore.value }
                    .distinctUntilChanged()
                    .filter { it }
                    .collect {
                        onAction(HomeAction.OnFetchNextDigimonList)
                    }
            }

            LazyVerticalGrid(
                state = lazyGridState,
                contentPadding = PaddingValues(6.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(
                    items = digimons,
                    key = { digimon -> digimon.id }
                ) { digimon ->

                    DigimonItem(
                        imageUrl = digimon.image ?: "",
                        name = digimon.name ?: "",
                        id = digimon.id,
                        onClick = { id ->
                            onAction(HomeAction.OnDigimonClick(id))
                        }
                    )
                }

                if (state.isLoading && digimons.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.Black,
                            )
                        }
                    }
                }

            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                )
            }
        }
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    DigimonAdventureComposeTheme {
        HomeScreen(
            state = HomeUiState(),
            lazyGridState = rememberLazyGridState(),
            onAction = { }
        )
    }
}



