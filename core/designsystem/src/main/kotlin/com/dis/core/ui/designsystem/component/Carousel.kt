package com.dis.core.ui.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil3.compose.AsyncImage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { images.size }
    val currentPageOffsetFraction by remember {
        derivedStateOf { pagerState.currentPageOffsetFraction }
    }

    Column(
        modifier =
            modifier
                .defaultMinSize(minHeight = 240.dp)
                .fillMaxWidth(),
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = "Digimon Image",
                contentScale = ContentScale.FillBounds,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .graphicsLayer {
                            val pageOffset =
                                (pagerState.currentPage - page + currentPageOffsetFraction).absoluteValue

                            lerp(
                                start = 75.dp,
                                stop = 100.dp,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f),
                            ).also { scale ->
                                scaleY = scale / 100.dp
                            }
                        },
            )
        }
    }
}
