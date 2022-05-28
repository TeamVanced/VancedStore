package com.vanced.store.ui.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.vanced.store.ui.theme.VSTheme

inline fun LazyGridScope.category(
    crossinline title: @Composable () -> Unit,
    block: LazyGridScope.() -> Unit,
) {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        ProvideTextStyle(VSTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium)) {
            title()
        }
    }
    block()
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        Spacer(Modifier.height(VSTheme.spacing.small))
    }
}