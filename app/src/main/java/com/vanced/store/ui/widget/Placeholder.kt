package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vanced.store.ui.theme.VSTheme

@Composable
fun PlaceholderBox(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape
) {
    Box(
        modifier = modifier
            .clip(shape)
            .placeholder(
                visible = true,
                color = VSTheme.colorScheme.onSurface,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = VSTheme.colorScheme.onSurfaceVariant
                )
            )
    )
}