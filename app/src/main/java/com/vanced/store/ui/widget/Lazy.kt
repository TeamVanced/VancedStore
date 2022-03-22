package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.theme.VSTheme

@Composable
fun CardLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    scrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerLarge),
        contentPadding = PaddingValues(VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
    )
}

@Composable
fun CardLazyVerticalGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    scrollEnabled: Boolean = true,
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = state,
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium),
        horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium),
        contentPadding = PaddingValues(VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
    )
}