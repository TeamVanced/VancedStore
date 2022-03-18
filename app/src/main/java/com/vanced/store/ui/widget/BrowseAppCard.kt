package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vanced.store.ui.theme.VSTheme

@Composable
fun LoadingBrowseAppCard(
    modifier: Modifier = Modifier
) {
    BaseBrowseAppCard(
        modifier = modifier,
        icon = {
            Box(modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .vsPlaceholder())
        },
        title = {
            Box(modifier = Modifier
                .weight(1f)
                .height(24.dp)
                .clip(VSTheme.shapes.medium)
                .vsPlaceholder())
        },
        description = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .clip(VSTheme.shapes.medium)
                .vsPlaceholder())
        },
        labels = {
            Box(modifier = Modifier
                .width(70.dp)
                .height(20.dp)
                .clip(CircleShape)
                .vsPlaceholder())
            Box(modifier = Modifier
                .width(65.dp)
                .height(20.dp)
                .clip(CircleShape)
                .vsPlaceholder())
        }
    )
}

private fun Modifier.vsPlaceholder() = composed {
    placeholder(
        visible = true,
        color = VSTheme.colorScheme.onSurface,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = VSTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun BaseBrowseAppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    icon: @Composable RowScope.() -> Unit,
    title: @Composable RowScope.() -> Unit,
    description: @Composable () -> Unit,
    labels: @Composable () -> Unit,
) {
    val content = @Composable {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(VSTheme.spacing.innerEdge),
            verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge)
            ) {
                icon()
                title()
            }
            description()
            FlowRow(
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                mainAxisSpacing = VSTheme.spacing.innerMedium,
                crossAxisSpacing = VSTheme.spacing.innerSmall
            ) {
                labels()
            }
        }
    }

    if (onClick != null) {
        Card(
            modifier = modifier,
            onClick = onClick,
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier
        ) {
            content()
        }
    }
}