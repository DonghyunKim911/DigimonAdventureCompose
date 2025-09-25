@file:OptIn(ExperimentalMaterial3Api::class)

package com.dis.feature.main.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.dis.core.navigation.BottomBarScreenSaver
import com.dis.core.navigation.MainRoute
import com.dis.core.navigation.bottomBarItems
import com.dis.feature.home.HomeScreenRoot

@Composable
fun NavigationRoot() {
    val backstack = rememberNavBackStack<MainRoute>(MainRoute.Home)

    var currentBottomBarScreen: MainRoute by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(MainRoute.Home) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Digimon Adventure",
                            color = Color.Black,
                        )
                    }
                },
                navigationIcon = {
                    if (currentBottomBarScreen == MainRoute.Detail) {
                        IconButton(onClick = {
                            backstack.removeLastOrNull()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                        onClick = { currentBottomBarScreen = destination },
                        icon = { Icon(imageVector = destination.icon, contentDescription = "$destination icon") },
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavDisplay(
            backStack = backstack,
            onBack = { backstack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<MainRoute.Home> {
                    HomeScreenRoot(Modifier.padding(innerPadding))
//                    HomeScreenRoot2(Modifier.padding(innerPadding))
                }

                entry<MainRoute.Search> {

                }

                entry<MainRoute.Detail> { key ->

                }

                entry<MainRoute.Bookmark> {

                }
            }
        )
    }


}
