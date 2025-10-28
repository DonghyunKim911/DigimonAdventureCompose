package com.dis.core.ui.designsystem.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateTo
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = White,
    title: String = "",
    imageUrl: String = "",
    minHeight: Dp = 56.dp,
    scrollBehavior: TopAppBarScrollBehavior,
    toolbarContent: @Composable BoxScope.(Float, String) -> Unit = { alpha, title -> },
    collapsingContent: @Composable BoxScope.(Float, String) -> Unit = { alpha, imageUrl -> },
) {
    var collapsingContentHeight by remember { mutableIntStateOf(0) }
    val offset = scrollBehavior.state.heightOffset // todo : derivedStateOf?

    val minHeightPx = LocalDensity.current.run { minHeight.toPx() }

    SideEffect {
        val limit = minHeightPx - collapsingContentHeight.toFloat()
        if (scrollBehavior.state.heightOffsetLimit != limit) {
            scrollBehavior.state.heightOffsetLimit = limit
        }
    }

    val appBarDragModifier =
        if (!scrollBehavior.isPinned) {
            Modifier.draggable(
                orientation = Orientation.Vertical,
                state =
                    rememberDraggableState { delta ->
                        scrollBehavior.state.heightOffset = offset + delta
                    },
                onDragStopped = { velocity ->
                    settleAppBar(
                        scrollBehavior.state,
                        velocity,
                        scrollBehavior.flingAnimationSpec,
                        scrollBehavior.snapAnimationSpec,
                    )
                },
            )
        } else {
            Modifier
        }

    val baseOffset = if (scrollBehavior.state.heightOffsetLimit > minHeightPx) -minHeightPx else (minHeightPx - collapsingContentHeight)
    val titleCollapsedFraction = offset / baseOffset
    val collapsedTitleAlpha =
        CubicBezierEasing(.8f, 0f, .8f, .15f).transform(titleCollapsedFraction)
    val expandedTitleAlpha = 1f - titleCollapsedFraction

    Surface(
        modifier =
            modifier
                .background(backgroundColor.copy(alpha = collapsedTitleAlpha))
                .then(appBarDragModifier),
    ) {
        Layout(
            modifier = Modifier,
            content = {
                Box(
                    Modifier
                        .layoutId("toolbar")
                        .fillMaxWidth()
                        .height(minHeight)
                        .background(backgroundColor.copy(alpha = collapsedTitleAlpha)),
                ) {
                    toolbarContent(collapsedTitleAlpha, title)
                }
                Box(
                    modifier =
                        Modifier
                            .layoutId("collapsingContent"),
                ) {
                    collapsingContent(expandedTitleAlpha, imageUrl)
                }
            },
        ) { measurables, constraints ->
            val ccPlaceable =
                measurables.first { it.layoutId == "collapsingContent" }.measure(constraints)
            val tbPlaceable =
                measurables.first { it.layoutId == "toolbar" }.measure(constraints)

            collapsingContentHeight = max(ccPlaceable.height, tbPlaceable.height)

            val maxWidth =
                listOf(ccPlaceable.width, tbPlaceable.width).max()
            val currentHeight =
                collapsingContentHeight + scrollBehavior.state.heightOffset

            layout(maxWidth, currentHeight.toInt()) {
                ccPlaceable.placeRelative(
                    0,
                    offset.roundToInt(),
                )
                tbPlaceable.placeRelative(0, 0)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private suspend fun settleAppBar(
    state: TopAppBarState,
    velocity: Float,
    flingAnimationSpec: DecayAnimationSpec<Float>?,
    snapAnimationSpec: AnimationSpec<Float>?,
): Velocity {
    // Check if the app bar is completely collapsed/expanded. If so, no need to settle the app bar,
    // and just return Zero Velocity.
    // Note that we don't check for 0f due to float precision with the collapsedFraction
    // calculation.
    if (state.collapsedFraction < 0.01f || state.collapsedFraction == 1f) {
        return Velocity.Zero
    }
    var remainingVelocity = velocity
    // In case there is an initial velocity that was left after a previous user fling, animate to
    // continue the motion to expand or collapse the app bar.
    if (flingAnimationSpec != null && abs(velocity) > 1f) {
        var lastValue = 0f
        AnimationState(
            initialValue = 0f,
            initialVelocity = velocity,
        ).animateDecay(flingAnimationSpec) {
            val delta = value - lastValue
            val initialHeightOffset = state.heightOffset
            state.heightOffset = initialHeightOffset + delta
            val consumed = abs(initialHeightOffset - state.heightOffset)
            lastValue = value
            remainingVelocity = this.velocity
            // avoid rounding errors and stop if anything is unconsumed
            if (abs(delta - consumed) > 0.5f) this.cancelAnimation()
        }
    }
    // Snap if animation specs were provided.
    if (snapAnimationSpec != null) {
        if (state.heightOffset < 0 &&
            state.heightOffset > state.heightOffsetLimit
        ) {
            AnimationState(initialValue = state.heightOffset).animateTo(
                if (state.collapsedFraction < 0.5f) {
                    0f
                } else {
                    state.heightOffsetLimit
                },
                animationSpec = snapAnimationSpec,
            ) { state.heightOffset = value }
        }
    }

    return Velocity(0f, remainingVelocity)
}
