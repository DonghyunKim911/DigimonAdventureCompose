package com.dis.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.core.presentation.model.DescriptionModel
import com.core.presentation.model.DigimonModel
import com.core.presentation.model.FieldModel
import com.core.presentation.model.SkillModel
import com.dis.core.ui.designsystem.component.CollapsingAppBar
import com.dis.core.ui.designsystem.component.ImageCarousel
import com.dis.core.ui.designsystem.component.SkillItem
import com.dis.core.ui.designsystem.theme.Background
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme
import com.dis.core.ui.util.ObserveAsEvents
import com.dis.feature.tts.TtsController
import com.dis.feature.tts.TtsSpeakingIndicator
import com.dis.presentation.detail.DetailAction
import com.dis.presentation.detail.DetailEvent
import com.dis.presentation.detail.DetailUiState
import com.dis.presentation.detail.DetailViewModel
import kotlinx.collections.immutable.ImmutableList
import java.util.Locale

@Composable
fun DetailScreenRoot(
    id: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel<DetailViewModel, DetailViewModel.Factory>(
        creationCallback = { factory -> factory.create(digimonId = id) }
    ),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is DetailEvent.NavigateBack -> onBack()
        }

    }

    DetailScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(
    state: DetailUiState,
    onAction: (DetailAction) -> Unit,
    modifier: Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            CollapsingAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                backgroundColor = Background,
                title = state.digimon?.name.orEmpty(),
                imageUrl = state.digimon?.image?.first()?.href.orEmpty(),
                scrollBehavior = scrollBehavior,
                toolbarContent = { alpha, title ->
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 8.dp)
                            .clickable {
                                onAction(DetailAction.OnBack)
                            },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .alpha(alpha),
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    state.digimon?.let { digimonModel ->
                        Icon(
                            imageVector = if (digimonModel.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                                .clickable {
                                    onAction(DetailAction.OnFavoriteClick)
                                },
                            tint = if (digimonModel.isFavorite) Color.Red else Color.Unspecified,
                        )
                    }
                },
                collapsingContent = { alpha, imageUrl ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(278.dp)
                            .alpha(alpha),
                        model = imageUrl,
                        contentDescription = "Digimon Image",
                        contentScale = ContentScale.FillBounds
                    )
                },
            )

            state.digimon?.let { digimon ->
                DigimonContent(
                    scrollBehavior = scrollBehavior,
                    digimon = digimon
                )
            } ?: run {
                if (state.isError) {
                    Text(
                        text = "Sorry, We can't find any information of this digimon.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                }
            }

        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DigimonContent(
    scrollBehavior: TopAppBarScrollBehavior,
    digimon: DigimonModel
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalScroll(scrollState)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            val context = LocalContext.current

            var isSpeaking by remember { mutableStateOf(false) }
            val controller = remember {
                TtsController(
                    context = context,
                    onSpeakingChanged = { speaking ->
                        isSpeaking = speaking
                    }
                )
            }

            DisposableEffect(Unit) {
                onDispose { controller.release() }
            }

            Text(
                text = digimon.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            
            TtsSpeakingIndicator(
                isSpeaking = isSpeaking,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                    if (isSpeaking) {
                        controller.stop()
                    } else {
                        controller.speak(
                            text = digimon.description.first()?.description.toString(),
                            locale = Locale.US,
                            rate = 1.0f,
                            pitch = 1.0f,
                        )
                    }
                },
            )
        }

        Text(
            text = digimon.description.firstOrNull()?.description ?: "",
            fontSize = 14.sp
        )

        DigimonInfo(
            level = digimon.level.first()?.level ?: "",
            attribute = digimon.attribute.firstOrNull()?.attribute ?: "",
            type = digimon.type.firstOrNull()?.type ?: "",
        )

        DigimonFieldContent(digimon.field)

        Spacer(Modifier.height(8.dp))

        DigimonSkills(digimon.skills)

        Spacer(Modifier.height(8.dp))

        DigimonCarousel(
            images = digimon.nextEvolution.mapNotNull { it?.image }
        )

    }
}

@Composable
private fun DigimonInfo(
    level: String,
    attribute: String,
    type: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        DigimonInfoItem(
            category = "Level",
            content = level,
        )
        DigimonInfoItem(
            category = "Attribute",
            content = attribute,
        )
        DigimonInfoItem(
            category = "Type",
            content = type,
        )
    }
}

@Composable
private fun DigimonInfoItem(
    category: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = category,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = content,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun DigimonFieldContent(
    field: ImmutableList<FieldModel?>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Fields",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            field.forEach { imageUrl ->
                AsyncImage(
                    modifier = Modifier.size(48.dp),
                    model = imageUrl?.image,
                    contentDescription = "Digimon Image",
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}

@Composable
fun DigimonSkills(
    skills: ImmutableList<SkillModel?>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Skills",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(8.dp))

        skills.take(5).forEach { skill ->
            key(skill?.id) {
                skill?.let {
                    Spacer(Modifier.height(8.dp))
                    SkillItem(
                        name = skill.skill ?: "",
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(
                                color = Color(color = 0xFFE8EDF2),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }
            }
        }

        if (skills.size > 5) {
            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        color = Color(color = 0xFF6EA4E8),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {

                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "See All",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }

    }
}

@Composable
private fun DigimonCarousel(
    images: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Next evolutions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(8.dp))

        ImageCarousel(images)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DigimonInfoPreview() {
    DigimonContent(
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        digimon = DigimonModel(
            name = "Agumon",
            id = 1,
            description = listOf(
                DescriptionModel(
                    description = "A Reptile Digimon with an appearance resembling a small dinosaur, it has grown and become able to walk on two legs. Its strength is weak as it is still in the process of growing, but it has a fearless and rather ferocious personality. Hard, sharp claws grow from both its hands and feet, and their power is displayed in battle. It also foreshadows an evolution into a great and powerful Digimon. Its Special Move is spitting a fiery breath from its mouth to attack the opponent (Baby Flame).",
                    language = "en_us",
                    origin = "reference_book",
                )
            )
        )
    )
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DigimonAdventureComposeTheme {
        DetailScreen(
            state = DetailUiState(),
            onAction = {},
            modifier = Modifier,
        )
    }
}
