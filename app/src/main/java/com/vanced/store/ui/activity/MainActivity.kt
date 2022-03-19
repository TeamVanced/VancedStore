package com.vanced.store.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vanced.store.ui.navigation.VSNavigation
import com.vanced.store.ui.navigation.VSNavigationScreen
import com.vanced.store.ui.navigation.rememberVSNavigatorBackstack
import com.vanced.store.ui.screen.BrowseScreen
import com.vanced.store.ui.screen.LibraryScreen
import com.vanced.store.ui.screen.SearchScreen
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            VSTheme(darkMode = true) {
                val systemUiController = rememberSystemUiController()
                val systemBarsColor = VSTheme.colorScheme.surface
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarsColor,
                        darkIcons = false
                    )
                }
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navigator = rememberVSNavigatorBackstack(initial = VSNavigationScreen.Browse)
        val currentScreen = navigator.current
        val bottomBarItems = VSNavigationScreen.bottomBarItems
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    currentScreen = currentScreen,
                    bottomBarItems = bottomBarItems,
                    onNavigate = { screen ->
                        navigator.navigate(screen)
                    }
                )
            }
        ) { paddingValues ->
            VSNavigation(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navigator = navigator,
                transitionSpec = { navigationTransitionSpec(bottomBarItems) },
                backPressEnabled = true,
                onBackPress = {
                    if (navigator.current is VSNavigationScreen.Browse) {
                        finish()
                    } else {
                        navigator.back()
                    }
                }
            ) { screen ->
                when (screen) {
                    is VSNavigationScreen.Browse -> {
                        BrowseScreen(
                            modifier = Modifier.fillMaxSize(),
                            onSearchClick = {
                                navigator.navigate(VSNavigationScreen.Search)
                            }
                        )
                    }
                    is VSNavigationScreen.Library -> {
                        LibraryScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is VSNavigationScreen.Search -> {
                        SearchScreen(
                            modifier = Modifier.fillMaxSize(),
                            onBackClick = {
                                navigator.back()
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomBar(
        currentScreen: VSNavigationScreen,
        bottomBarItems: Array<VSNavigationScreen>,
        onNavigate: (VSNavigationScreen) -> Unit,
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = currentScreen in bottomBarItems,
            enter = slideInVertically { it / 2 } + fadeIn(),
            exit = slideOutVertically { it / 2 } + fadeOut(),
        ) {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                for (screen in VSNavigationScreen.bottomBarItems) {
                    VSScreenNavigationBarItem(
                        screen = screen,
                        selected = currentScreen == screen,
                        onClick = {
                            if (currentScreen != screen) {
                                onNavigate(screen)
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun RowScope.VSScreenNavigationBarItem(
        screen: VSNavigationScreen,
        selected: Boolean,
        onClick: () -> Unit,
    ) {
        NavigationBarItem(
            selected = selected,
            icon = {
                Icon(
                    painter = painterResource(screen.iconRes),
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(screen.labelRes))
            },
            onClick = onClick
        )
    }

    fun <S> AnimatedContentScope<S>.navigationTransitionSpec(
        bottomBarItems: Array<VSNavigationScreen>
    ): ContentTransform where S : VSNavigationScreen {
        val initialIndex = bottomBarItems.indexOf(initialState)
        val targetIndex = bottomBarItems.indexOf(targetState)

        return if (initialIndex != -1 && targetIndex != -1) {
            if (initialIndex < targetIndex) {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Start
                ) with slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Start
                )
            } else {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.End
                ) with slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.End
                )
            }
        } else {
            fadeIn() with fadeOut()
        }
    }

}