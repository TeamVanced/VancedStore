package com.vanced.store.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vanced.store.R
import com.vanced.store.ui.theme.VSTheme

private val GroupRowContainerHeight = 40.dp
private val GroupRowItemMinWidth = 58.dp

@Composable
fun SegmentedButtonRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    VSOutlinedCard(
        modifier = modifier,
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier
                .height(GroupRowContainerHeight)
                .widthIn(min = GroupRowItemMinWidth),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Suppress("unused")
@Composable
fun RowScope.SegmentedButton(
    onClick: () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable () -> Unit,
) {
    val containerColor =
        if (selected)
            VSTheme.colorScheme.secondaryContainer
        else
            VSTheme.colorScheme.surface
    OutlinedButton(
        modifier = modifier.weight(1f),
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        AnimatedVisibility(visible = selected) {
            Row {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
            }
        }
        label()
    }
}