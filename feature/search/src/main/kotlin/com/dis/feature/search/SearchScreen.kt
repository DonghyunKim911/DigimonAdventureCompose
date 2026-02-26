package com.dis.feature.search

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dis.core.ui.designsystem.component.DigimonItem
import com.dis.core.ui.designsystem.theme.Background
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme
import com.dis.core.ui.util.ObserveAsEvents
import com.dis.presentation.search.SearchAction
import com.dis.presentation.search.SearchEvent
import com.dis.presentation.search.SearchUiState
import com.dis.presentation.search.SearchViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun SearchScreenRoot(
    navigateToDigimonDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is SearchEvent.NavigateToDigimonDetail -> {
                navigateToDigimonDetail(event.id)
            }
        }
    }

    SearchScreen(
        state = state,
        lazyGridState = lazyGridState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen(
    state: SearchUiState,
    lazyGridState: LazyGridState,
    onAction: (SearchAction) -> Unit,
    modifier: Modifier = Modifier,
    threshold: Int = 8,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Search",
                    color = Color.Black,
                )
            },
        )

        OutlinedTextField(
            value = state.query,
            onValueChange = { query ->
                onAction(SearchAction.OnQueryChanged(query))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            },
            placeholder = {
                Text(text = "Search Digimon by name")
            },
            singleLine = true,
        )

        SearchResultContent(
            state = state,
            lazyGridState = lazyGridState,
            onAction = onAction,
            threshold = threshold,
        )
    }
}

@Composable
private fun SearchResultContent(
    state: SearchUiState,
    lazyGridState: LazyGridState,
    onAction: (SearchAction) -> Unit,
    threshold: Int,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val digimons = state.digimonList
        val shouldLoadMore =
            remember(
                lazyGridState,
                state.digimonList.size,
            ) {
                derivedStateOf {
                    val totalItemsCount = lazyGridState.layoutInfo.totalItemsCount
                    val lastVisibleItemIndex =
                        lazyGridState.layoutInfo.visibleItemsInfo
                            .lastOrNull()
                            ?.index ?: 0
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
                    onAction(SearchAction.OnFetchNextDigimonList)
                }
        }

        if (!state.isLoading && digimons.isEmpty()) {
            Text(
                text =
                    if (state.query.isBlank()) {
                        "Please enter a search term."
                    } else {
                        "No results for \"${state.query}\""
                    },
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
            )
            return
        }

        LazyVerticalGrid(
            state = lazyGridState,
            contentPadding = PaddingValues(6.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(
                items = digimons,
                key = { digimon -> digimon.id },
            ) { digimon ->
                DigimonItem(
                    imageUrl = digimon.image.orEmpty(),
                    name = digimon.name.orEmpty(),
                    id = digimon.id,
                    onClick = { id ->
                        onAction(SearchAction.OnDigimonClick(id))
                    },
                )
            }

            if (state.isLoading && digimons.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
        }

        if (state.isLoading && digimons.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
            )
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    DigimonAdventureComposeTheme {
        SearchScreen(
            state = SearchUiState(),
            lazyGridState = rememberLazyGridState(),
            onAction = { },
        )
    }
}
