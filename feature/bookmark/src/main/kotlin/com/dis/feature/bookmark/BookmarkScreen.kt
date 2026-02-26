package com.dis.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.dis.presentation.bookmark.BookmarkAction
import com.dis.presentation.bookmark.BookmarkEvent
import com.dis.presentation.bookmark.BookmarkUiState
import com.dis.presentation.bookmark.BookmarkViewModel

@Composable
fun BookmarkScreenRoot(
    navigateToDigimonDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel<BookmarkViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is BookmarkEvent.NavigateToDigimonDetail -> {
                navigateToDigimonDetail(event.id)
            }
        }
    }

    BookmarkScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookmarkScreen(
    state: BookmarkUiState,
    onAction: (BookmarkAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Bookmark",
                    color = Color.Black,
                )
            },
        )

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                )
                return@Box
            }

            if (state.digimonList.isEmpty()) {
                Text(
                    text = "No bookmarked Digimon yet.",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp),
                )
                return@Box
            }

            LazyVerticalGrid(
                contentPadding = PaddingValues(6.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(
                    items = state.digimonList,
                    key = { digimon -> digimon.id },
                ) { digimon ->
                    DigimonItem(
                        imageUrl = digimon.image.orEmpty(),
                        name = digimon.name.orEmpty(),
                        id = digimon.id,
                        onClick = { id ->
                            onAction(BookmarkAction.OnDigimonClick(id))
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    DigimonAdventureComposeTheme {
        BookmarkScreen(
            state = BookmarkUiState(),
            onAction = { },
        )
    }
}
