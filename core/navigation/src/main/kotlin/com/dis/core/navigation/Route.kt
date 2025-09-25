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
import kotlinx.serialization.Serializable

sealed interface Route: NavKey {

    @Serializable
    data object Splash: Route

    @Serializable
    data object Main: Route

}

sealed interface MainRoute: NavKey {

    val icon: ImageVector

    @Serializable
    data object Home: MainRoute {
        override val icon = Icons.Default.Home
    }

    @Serializable
    data class Detail(val id: String)

    @Serializable
    data object Search: MainRoute {
        override val icon = Icons.Default.Search

    }

    @Serializable
    data object Bookmark: MainRoute {
        override val icon = Icons.Default.Bookmark
    }

}

val bottomBarItems = listOf<MainRoute>(
    MainRoute.Home,
    MainRoute.Search,
    MainRoute.Bookmark,
)

val BottomBarScreenSaver = Saver<MainRoute, String>(
    save = { it::class.java.name ?: "Unknown" },
    restore = {
        when(it) {
            MainRoute.Home::class.java.name -> MainRoute.Home
            MainRoute.Search::class.java.name -> MainRoute.Search
            MainRoute.Bookmark::class.java.name -> MainRoute.Bookmark
            else -> MainRoute.Home
        }
    }
)


fun Route.startActivity(
    packageContext: Context,
    activityClass: Class<out Activity>
) {
    val intent = Intent(packageContext, activityClass)
    packageContext.startActivity(intent)
}

fun MainRoute.startActivity(
    packageContext: Context,
    activityClass: Class<out Activity>
) {
    val intent = Intent(packageContext, activityClass)
    packageContext.startActivity(intent)
}
