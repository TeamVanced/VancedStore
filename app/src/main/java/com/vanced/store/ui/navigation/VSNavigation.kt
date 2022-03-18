package com.vanced.store.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VSNavigation(
    navigator: VSNavigator,
    modifier: Modifier = Modifier,
    transitionSpec: AnimatedContentScope<VSNavigationScreen>.() -> ContentTransform,
    backPressEnabled: Boolean,
    onBackPress: () -> Unit,
    content: @Composable (VSNavigationScreen) -> Unit,
) {
//    val saveableStateHolder = rememberSaveableStateHolder()
    BackHandler(enabled = backPressEnabled) {
        onBackPress()
    }

    AnimatedContent(
        modifier = modifier,
        targetState = navigator.current,
        transitionSpec = transitionSpec
    ) { animatedCurrentItem ->
//        saveableStateHolder.SaveableStateProvider(animatedCurrentItem.route) {
            content(animatedCurrentItem)
//        }
    }
}