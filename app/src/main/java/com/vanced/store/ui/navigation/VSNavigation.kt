package com.vanced.store.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
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
    val saveableStateHolder = rememberSaveableStateHolder()
    BackHandler(
        enabled = backPressEnabled,
        onBack = onBackPress
    )
    AnimatedContent(
        modifier = modifier,
        targetState = navigator.current,
        transitionSpec = transitionSpec
    ) { animatedCurrentItem ->
        saveableStateHolder.SaveableStateProvider(animatedCurrentItem) {
            content(animatedCurrentItem)
        }
    }
}