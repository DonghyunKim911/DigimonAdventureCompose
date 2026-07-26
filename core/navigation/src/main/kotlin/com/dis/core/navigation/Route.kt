package com.dis.core.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.core.presentation.model.SkillModel
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Main : Route

    @Serializable
    data class Detail(
        val id: Int,
    ) : Route

    @Serializable
    data class DetailSkillList(
        val skills: List<SkillModel?>,
    ) : Route
}

sealed interface BottomRoute : NavKey {
    val icon: ImageVector
    val label: String

    @Serializable
    data object Home : BottomRoute {
        override val icon = Icons.Default.Home
        override val label = "Home"
    }

    @Serializable
    data object Search : BottomRoute {
        override val icon = Icons.Default.Search
        override val label = "Search"
    }

    @Serializable
    data object Bookmark : BottomRoute {
        override val icon = Icons.Default.Bookmark
        override val label = "Bookmark"
    }
}

val bottomBarItems =
    listOf(
        BottomRoute.Home,
        BottomRoute.Search,
        BottomRoute.Bookmark,
    )

val BottomBarScreenSaver =
    Saver<BottomRoute, String>(
        save = { it::class.java.name ?: "Unknown" },
        restore = {
            when (it) {
                BottomRoute.Home::class.java.name -> BottomRoute.Home
                BottomRoute.Search::class.java.name -> BottomRoute.Search
                BottomRoute.Bookmark::class.java.name -> BottomRoute.Bookmark
                else -> BottomRoute.Home
            }
        },
    )

fun Route.startActivity(
    packageContext: Context,
    activityClass: Class<out Activity>,
) {
    val intent = Intent(packageContext, activityClass)
    packageContext.startActivity(intent)
}

fun BottomRoute.startActivity(
    packageContext: Context,
    activityClass: Class<out Activity>,
) {
    val intent = Intent(packageContext, activityClass)
    packageContext.startActivity(intent)
}
