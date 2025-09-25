package com.dis.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreenRoot(
    id: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DetailScreen(modifier)
}

@Composable
private fun DetailScreen(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.size(200.dp),
            text = "Detail Screen",
            fontSize = 48.sp,
            color = Color.Black
        )
    }

}
