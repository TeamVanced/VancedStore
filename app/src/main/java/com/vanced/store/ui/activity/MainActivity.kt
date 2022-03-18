package com.vanced.store.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
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
import com.vanced.store.ui.navigation.rememberVSNavigator
import com.vanced.store.ui.screen.BrowseScreen
import com.vanced.store.ui.screen.LibraryScreen
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
        val navigator = rememberVSNavigator(initial = VSNavigationScreen.Browse)
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                NavigationBar(modifier = Modifier.fillMaxWidth()) {
                    for (screen in VSNavigationScreen.bottomBarItems) {
                        VSScreenNavigationBarItem(
                            screen = screen,
                            selected = navigator.current == screen,
                            onClick = {
                                if (navigator.current != screen) {
                                    navigator.navigate(screen)
                                }
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            VSNavigation(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navigator = navigator,
                transitionSpec = {
                    when {
                        VSNavigationScreen.Browse isTransitioningTo VSNavigationScreen.Library -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Start
                            ) with slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.Start
                            )
                        }
                        VSNavigationScreen.Library isTransitioningTo VSNavigationScreen.Browse -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.End
                            ) with slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.End
                            )
                        }
                        else -> {
                            fadeIn() with fadeOut()
                        }
                    }
                },
                backPressEnabled = true,
                onBackPress = {
                    if (!navigator.back()) {
                        finish()
                    }
                }
            ) { screen ->
                when (screen) {
                    is VSNavigationScreen.Browse -> {
                        BrowseScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is VSNavigationScreen.Library -> {
                        LibraryScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun RowScope.VSScreenNavigationBarItem(
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

}