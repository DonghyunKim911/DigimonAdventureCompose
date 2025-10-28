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
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

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
    innerPadding: Dp = 6.dp, // 원 가장자리와 막대 사이 여백
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
        val r = min(size.width, size.height) / 2f
        val cx = size.width / 2f
        val cy = size.height / 2f

        // 배경 원
        drawCircle(color = backgroundColor, radius = r, center = Offset(cx, cy))

        // 여백 포함 원 안쪽 사용 가능 폭/높이
        val pad = innerPadding.toPx()
        val usableW = 2f * r - 2f * pad
        val usableH = 2f * r - 2f * pad

        // 막대 폭을 원 안에 꼭 맞게 보정(가로 중앙 정렬)
        val gapPx = gap.toPx()
        val desiredBarW = barWidth.toPx()
        val maxBarW = (usableW - gapPx * (bars - 1)) / bars
        val barW = max(1f, min(desiredBarW, maxBarW))

        val totalBarsW = bars * barW + (bars - 1) * gapPx
        val startX = cx - totalBarsW / 2f

        // 높이 범위 (세로 중앙 기준 양방향으로 성장)
        val minH = minBarHeight.toPx()
        val maxH = min(maxBarHeight.toPx(), usableH) // 원 안을 넘지 않게
        val idleH = idleHeight.toPx().coerceIn(minH, maxH)

        // 원형 마스크
        val circlePath =
            Path().apply {
                addOval(Rect(cx - r, cy - r, cx + r, cy + r))
            }

        clipPath(circlePath) {
            var x = startX
            val radiusPx = cornerRadius.toPx()

            repeat(bars) { i ->
                // 0..1 값 → 높이로 보간
                val t = anims[i].value
                val targetH = if (isSpeaking) (minH + (maxH - minH) * t) else idleH
                val h = targetH.coerceIn(1f, maxH)

                // 세로 ‘중앙’을 기준으로 위/아래로 같은 만큼
                val topY = cy - h / 2f

                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(x, topY),
                    size = Size(barW, h),
                    cornerRadius = CornerRadius(radiusPx, radiusPx),
                )
                x += barW + gapPx
            }
        }
    }
}
