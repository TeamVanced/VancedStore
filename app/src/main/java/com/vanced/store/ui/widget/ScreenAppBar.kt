package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = navigationIcon,
        actions = actions,
        scrollBehavior = scrollBehavior
    )
}