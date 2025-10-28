package com.dis.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.presentation.model.SkillModel
import com.dis.core.ui.designsystem.component.SkillItem
import com.dis.core.ui.designsystem.theme.Background

@Composable
fun DetailSKillListScreenRoot(
    onBack: () -> Unit,
    skills: List<SkillModel?>,
    modifier: Modifier = Modifier,
) {
    DetailSKillListScreen(
        onBack = onBack,
        skills = skills,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailSKillListScreen(
    onBack: () -> Unit,
    skills: List<SkillModel?>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        TopAppBar(
            title = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(color = Background),
                ) {
                    Text(
                        text = "SKills",
                        color = Color.Black,
                    )
                }
            },
            navigationIcon = {
                Box {
                    Image(
                        modifier =
                            Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 8.dp)
                                .clickable { onBack() },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },
        )

        LazyColumn(
            modifier =
                Modifier
                    .background(color = Background),
            contentPadding = PaddingValues(6.dp),
        ) {
            items(
                items = skills,
                key = { skill -> skill?.id ?: 0 }, // id must not be null.
            ) { skill ->
                skill?.let {
                    Spacer(Modifier.height(8.dp))
                    SkillItem(
                        name = it.skill ?: "",
                        description = it.description ?: "",
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(
                                    color = Color(color = 0xFFE8EDF2),
                                    shape = RoundedCornerShape(6.dp),
                                ),
                    )
                }
            }
        }
    }
}
