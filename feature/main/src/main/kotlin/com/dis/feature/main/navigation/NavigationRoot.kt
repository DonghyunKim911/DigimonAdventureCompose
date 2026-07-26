@file:OptIn(ExperimentalMaterial3Api::class)

package com.dis.feature.main.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
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
import com.dis.feature.search.SearchScreenRoot

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationRoot() {
    val homeBackStack = rememberNavBackStack<NavKey>(BottomRoute.Home)
    val searchBackStack = rememberNavBackStack<NavKey>(BottomRoute.Search)
    val bookmarkBackStack = rememberNavBackStack<NavKey>(BottomRoute.Bookmark)
    val tabStateHolder = rememberSaveableStateHolder()

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
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.label,
                            )
                        },
                        label = { Text(destination.label) },
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        tabStateHolder.SaveableStateProvider(tabStateKey(currentBottomBarScreen)) {
            BottomTabNavDisplay(
                currentBottomBarScreen = currentBottomBarScreen,
                homeBackStack = homeBackStack,
                searchBackStack = searchBackStack,
                bookmarkBackStack = bookmarkBackStack,
                onNavigateToBottomRoute = { currentBottomBarScreen = it },
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun BottomTabNavDisplay(
    currentBottomBarScreen: BottomRoute,
    homeBackStack: MutableList<NavKey>,
    searchBackStack: MutableList<NavKey>,
    bookmarkBackStack: MutableList<NavKey>,
    onNavigateToBottomRoute: (BottomRoute) -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentBackStack =
        when (currentBottomBarScreen) {
            BottomRoute.Home -> homeBackStack
            BottomRoute.Search -> searchBackStack
            BottomRoute.Bookmark -> bookmarkBackStack
        }

    BackHandler(
        enabled = currentBottomBarScreen != BottomRoute.Home && currentBackStack.size == 1,
    ) {
        onNavigateToBottomRoute(BottomRoute.Home)
    }

    NavDisplay(
        backStack = currentBackStack,
        onBack = { count ->
            repeat(count.coerceAtMost(currentBackStack.lastIndex)) {
                currentBackStack.removeLastOrNull()
            }
        },
        entryDecorators =
            listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<BottomRoute.Home>(
                    clazzContentKey = { key -> tabScopedContentKey(BottomRoute.Home, key) },
                ) {
                    HomeScreenRoot(
                        navigateToDigimonDetail = { id ->
                            homeBackStack.add(Route.Detail(id))
                        },
                        modifier = modifier,
                    )
                }

                entry<BottomRoute.Search>(
                    clazzContentKey = { key -> tabScopedContentKey(BottomRoute.Search, key) },
                ) {
                    SearchScreenRoot(
                        navigateToDigimonDetail = { id ->
                            searchBackStack.add(Route.Detail(id))
                        },
                        modifier = modifier,
                    )
                }

                entry<BottomRoute.Bookmark>(
                    clazzContentKey = { key -> tabScopedContentKey(BottomRoute.Bookmark, key) },
                ) {
                    BookmarkScreenRoot(
                        navigateToDigimonDetail = { id ->
                            bookmarkBackStack.add(Route.Detail(id))
                        },
                        modifier = modifier,
                    )
                }

                entry<Route.Detail>(
                    clazzContentKey = { key -> tabScopedContentKey(currentBottomBarScreen, key) },
                ) { key ->
                    DetailScreenRoot(
                        id = key.id,
                        onBack = { currentBackStack.removeLastOrNull() },
                        onNavigateToSkillList = { skills ->
                            currentBackStack.add(Route.DetailSkillList(skills))
                        },
                        modifier = modifier,
                    )
                }

                entry<Route.DetailSkillList>(
                    clazzContentKey = { key -> tabScopedContentKey(currentBottomBarScreen, key) },
                ) { key ->
                    DetailSKillListScreenRoot(
                        skills = key.skills,
                        onBack = { currentBackStack.removeLastOrNull() },
                        modifier = modifier,
                    )
                }
            },
    )
}

private fun tabStateKey(route: BottomRoute): String = route::class.java.name

private fun tabScopedContentKey(
    tab: BottomRoute,
    route: Any,
): String = "${tabStateKey(tab)}:$route"
