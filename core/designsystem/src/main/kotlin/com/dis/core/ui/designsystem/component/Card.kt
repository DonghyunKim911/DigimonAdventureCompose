package com.dis.core.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme

@Composable
fun DigimonItem(
    imageUrl: String,
    name: String,
    id: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth()
                .clickable { onClick(id) },
            shape = RoundedCornerShape(14.dp),
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .background(color = Color.White),
                contentScale = ContentScale.FillBounds,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = "Digimon Card Image",
                onSuccess = { success -> }
            )
        }

        BasicText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            text = name,
            autoSize = TextAutoSize.StepBased(
                maxFontSize = 16.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            color = { Color.Black },
        )
    }

}

@Preview
@Composable
private fun DigimonCardPreview() {
    DigimonAdventureComposeTheme {
        DigimonItem(
            imageUrl = "https://digi-api.com/images/digimon/w/Aquilamon.png",
            name = "Digimon",
            id = 0,
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}