package com.vanced.store.ui.widget

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@Composable
fun ScreenScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable (TopAppBarScrollBehavior) -> Unit,
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit
) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { topBar(scrollBehavior) },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = content
    )
}