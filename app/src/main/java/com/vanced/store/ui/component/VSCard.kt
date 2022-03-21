package com.vanced.store.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    containerColor: Color = VSTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    border: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            contentColor = contentColor,
            border = border,
            elevation = elevation,
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            contentColor = contentColor,
            border = border,
            elevation = elevation,
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
    containerColor: Color = VSTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            contentColor = contentColor,
            elevation = elevation,
        ) {
            content()
        }
    } else {
        ElevatedCard(
            modifier = modifier,
            shape = shape,
            contentColor = contentColor,
            elevation = elevation,
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
    containerColor: Color = VSTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    border: BorderStroke = BorderStroke(1.dp, VSTheme.colorScheme.outline),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    content: @Composable () -> Unit
) {
    if (onClick != null && interactionSource != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            contentColor = contentColor,
            border = border,
            elevation = elevation,
        ) {
            content()
        }
    } else {
        OutlinedCard(
            modifier = modifier,
            shape = shape,
            contentColor = contentColor,
            border = border,
            elevation = elevation,
        ) {
            content()
        }
    }
}