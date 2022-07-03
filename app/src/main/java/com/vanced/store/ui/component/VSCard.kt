package com.vanced.store.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.theme.VSTheme

@Composable
fun VSCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? =
        remember { if (onClick != null) MutableInteractionSource() else null },
    shape: Shape = VSTheme.shapes.large,
    border: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            border = border,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            border = border,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    }
}

@Composable
fun VSElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? =
        remember { if (onClick != null) MutableInteractionSource() else null },
    shape: Shape = VSTheme.shapes.large,
    elevation: CardElevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 4.dp
    ),
    colors: CardColors = CardDefaults.elevatedCardColors(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    } else {
        ElevatedCard(
            modifier = modifier,
            shape = shape,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    }
}

@Composable
fun VSOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? =
        remember { if (onClick != null) MutableInteractionSource() else null },
    shape: Shape = VSTheme.shapes.large,
    border: BorderStroke = BorderStroke(1.dp, VSTheme.colorScheme.outline),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    colors: CardColors = CardDefaults.outlinedCardColors(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            border = border,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    } else {
        OutlinedCard(
            modifier = modifier,
            shape = shape,
            border = border,
            elevation = elevation,
            colors = colors,
        ) {
            content()
        }
    }
}