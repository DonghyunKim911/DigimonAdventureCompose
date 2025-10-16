package com.dis.core.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dis.core.ui.designsystem.theme.Background
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme

@Composable
fun SkillItem(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.Black,
        )

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick() },
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null
        )

    }
}

@Preview
@Composable
private fun SKillItemPreview() {
    DigimonAdventureComposeTheme {
        SkillItem(
            name = "Pepper Breath",
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = Color(0xFFE8EDF2), shape = RoundedCornerShape(6.dp))
        )
    }
}
