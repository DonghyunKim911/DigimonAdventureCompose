package com.dis.feature.tts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min
import kotlin.random.Random

private data class IndicatorLayout(
    val center: Offset,
    val radius: Float,
    val startX: Float,
    val barWidth: Float,
    val gap: Float,
    val minHeight: Float,
    val maxHeight: Float,
    val idleHeight: Float,
    val cornerRadius: Float,
)

@Composable
fun TtsSpeakingIndicator(
    isSpeaking: Boolean,
    modifier: Modifier = Modifier,
    diameter: Dp = 44.dp, // 원 크기
    bars: Int = 5,
    minBarHeight: Dp = 6.dp,
    maxBarHeight: Dp = 16.dp,
    barWidth: Dp = 3.dp,
    gap: Dp = 3.dp,
    barColor: Color = Color(0xFF111827), // 막대 색
    backgroundColor: Color = Color(0xFFEBEDF2), // 원형 배경색
    innerPadding: Dp = 6.dp, // 원 가장자리와 막대 사이 여백원
    cornerRadius: Dp = 2.dp,
    refreshMillis: Long = 110L,
    idleHeight: Dp = 6.dp,
) {
    // px 변환은 DrawScope 안에서 직접 계산 (캔버스 크기에 비례시킬 수 있음)
    val anims = remember(bars) { List(bars) { Animatable(0f) } }

    // speaking 애니메이션 루프
    LaunchedEffect(isSpeaking) {
        if (!isSpeaking) {
            anims.forEach { a -> launch { a.animateTo(0f, tween(180)) } }
            return@LaunchedEffect
        }
        while (isSpeaking) {
            anims.forEachIndexed { i, a ->
                val r = Random.nextFloat()
                launch {
                    delay((i * 12L) % 90L)
                    a.animateTo(r, tween((refreshMillis * 0.9f).toInt()))
                }
            }
            delay(refreshMillis)
        }
    }

    Canvas(modifier.size(diameter)) {
        val radius = min(size.width, size.height) / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        // 배경 원
        drawCircle(color = backgroundColor, radius = radius, center = center)

        val layout =
            calculateIndicatorLayout(
                bars = bars,
                innerPadding = innerPadding,
                barWidth = barWidth,
                gap = gap,
                minBarHeight = minBarHeight,
                maxBarHeight = maxBarHeight,
                idleHeight = idleHeight,
                cornerRadius = cornerRadius,
            ) ?: return@Canvas

        // 원형 마스크
        val circlePath =
            Path().apply {
                addOval(
                    Rect(
                        left = layout.center.x - layout.radius,
                        top = layout.center.y - layout.radius,
                        right = layout.center.x + layout.radius,
                        bottom = layout.center.y + layout.radius,
                    ),
                )
            }

        clipPath(circlePath) {
            var x = layout.startX

            repeat(bars) { i ->
                // 0..1 값 → 높이로 보간
                val t = anims[i].value
                val targetH =
                    if (isSpeaking) {
                        layout.minHeight + (layout.maxHeight - layout.minHeight) * t
                    } else {
                        layout.idleHeight
                    }
                val h = targetH.coerceIn(0f, layout.maxHeight)
                val radiusPx = min(layout.cornerRadius, min(layout.barWidth, h) / 2f)

                // 세로 ‘중앙’을 기준으로 위/아래로 같은 만큼
                val topY = layout.center.y - h / 2f

                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(x, topY),
                    size = Size(layout.barWidth, h),
                    cornerRadius = CornerRadius(radiusPx, radiusPx),
                )
                x += layout.barWidth + layout.gap
            }
        }
    }
}

private fun DrawScope.calculateIndicatorLayout(
    bars: Int,
    innerPadding: Dp,
    barWidth: Dp,
    gap: Dp,
    minBarHeight: Dp,
    maxBarHeight: Dp,
    idleHeight: Dp,
    cornerRadius: Dp,
): IndicatorLayout? {
    if (bars <= 0 || size.minDimension <= 0f) return null

    val radius = min(size.width, size.height) / 2f
    val center = Offset(size.width / 2f, size.height / 2f)
    val padding = min(innerPadding.toPx().coerceAtLeast(0f), radius)
    val usableWidth = (size.width - padding * 2f).coerceAtLeast(0f)
    val usableHeight = (size.height - padding * 2f).coerceAtLeast(0f)

    if (usableWidth <= 0f || usableHeight <= 0f) return null

    val gapCount = (bars - 1).coerceAtLeast(0)
    val desiredBarWidth = barWidth.toPx().coerceAtLeast(1f)
    val desiredGap = gap.toPx().coerceAtLeast(0f)
    val desiredTotalWidth = bars * desiredBarWidth + gapCount * desiredGap
    val widthScale = if (desiredTotalWidth > 0f) min(1f, usableWidth / desiredTotalWidth) else 1f
    val fittedBarWidth = desiredBarWidth * widthScale
    val fittedGap = desiredGap * widthScale
    val totalBarsWidth = bars * fittedBarWidth + gapCount * fittedGap

    if (fittedBarWidth <= 0f || totalBarsWidth <= 0f) return null

    val maxHeight = min(maxBarHeight.toPx().coerceAtLeast(0f), usableHeight)
    if (maxHeight <= 0f) return null

    val minHeight = min(minBarHeight.toPx().coerceAtLeast(0f), maxHeight)
    val idleHeightPx = idleHeight.toPx().coerceAtLeast(0f).coerceIn(minHeight, maxHeight)
    val startX = center.x - totalBarsWidth / 2f

    return IndicatorLayout(
        center = center,
        radius = radius,
        startX = startX,
        barWidth = fittedBarWidth,
        gap = fittedGap,
        minHeight = minHeight,
        maxHeight = maxHeight,
        idleHeight = idleHeightPx,
        cornerRadius = cornerRadius.toPx().coerceAtLeast(0f),
    )
}
