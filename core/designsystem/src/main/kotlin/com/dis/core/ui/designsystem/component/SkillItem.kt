package com.dis.core.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dis.core.ui.designsystem.theme.Background
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme

@Composable
fun SkillItem(
    name: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = 8.dp),
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = description,
            fontSize = 12.sp,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun SKillItemPreview() {
    DigimonAdventureComposeTheme {
        SkillItem(
            name = "Baby Flame",
            description = "Spits a fiery breath from its mouth to attack the opponent.",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color(0xFFE8EDF2), shape = RoundedCornerShape(6.dp)),
        )
    }
}
