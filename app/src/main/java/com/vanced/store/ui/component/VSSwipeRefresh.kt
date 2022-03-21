package com.vanced.store.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.vanced.store.ui.theme.VSTheme

@Composable
fun VSSwipeRefresh(
    state: SwipeRefreshState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    SwipeRefresh(
        state = state,
        onRefresh = onRefresh,
        modifier = modifier,
        swipeEnabled = swipeEnabled,
        indicator = { state, refreshTriggerDistance ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTriggerDistance,
                scale = true,
                backgroundColor = VSTheme.colorScheme.surface,
                contentColor = VSTheme.colorScheme.primary
            )
        },
        content = content
    )
}