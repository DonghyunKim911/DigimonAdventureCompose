@file:OptIn(ExperimentalMaterial3Api::class)

package com.dis.feature.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.dis.core.navigation.BottomBarScreenSaver
import com.dis.core.navigation.BottomRoute
import com.dis.core.navigation.Route
import com.dis.core.navigation.bottomBarItems
import com.dis.feature.bookmark.BookmarkScreenRoot
import com.dis.feature.detail.DetailSKillListScreenRoot
import com.dis.feature.detail.DetailScreenRoot
import com.dis.feature.home.HomeScreenRoot

@Composable
fun NavigationRoot() {
    val backstack = rememberNavBackStack<BottomRoute>(BottomRoute.Home)

    var currentBottomBarScreen: BottomRoute by rememberSaveable(
        stateSaver = BottomBarScreenSaver,
    ) { mutableStateOf(BottomRoute.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                        onClick = {
                            currentBottomBarScreen = destination
                            backstack.clear()
                            backstack.add(destination)
                        },
                        icon = { Icon(imageVector = destination.icon, contentDescription = "$destination icon") },
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavDisplay(
            backStack = backstack,
            onBack = { backstack.removeLastOrNull() },
            entryDecorators =
                listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
            entryProvider =
                entryProvider {
                    entry<BottomRoute.Home> {
                        HomeScreenRoot(
                            navigateToDigimonDetail = { id ->
                                backstack.add(Route.Detail(id))
                            },
                            modifier = Modifier.padding(innerPadding),
                        )
                    }

                    entry<Route.Detail> { key ->
                        DetailScreenRoot(
                            id = key.id,
                            onBack = { backstack.removeLastOrNull() },
                            onNavigateToSkillList = { skills ->
                                backstack.add(Route.DetailSkillList(skills))
                            },
                            modifier = Modifier.padding(innerPadding),
                        )
                    }

                    entry<Route.DetailSkillList> { key ->
                        DetailSKillListScreenRoot(
                            skills = key.skills,
                            onBack = { backstack.removeLastOrNull() },
                            modifier = Modifier.padding(innerPadding),
                        )
                    }

                    entry<BottomRoute.Search> {
                    }

                    entry<BottomRoute.Bookmark> {
                        BookmarkScreenRoot(
                            navigateToDigimonDetail = { id ->
                                backstack.add(Route.Detail(id))
                            },
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                },
        )
    }
}
