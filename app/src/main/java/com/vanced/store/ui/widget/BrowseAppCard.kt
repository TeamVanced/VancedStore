package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vanced.store.ui.component.VSElevatedCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun LoadingBrowseAppCard(
    modifier: Modifier = Modifier
) {
    BaseBrowseAppCard(
        modifier = modifier,
        icon = {
            PlaceholderBox(
                modifier = Modifier
                    .size(36.dp))
        },
        title = {
            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(24.dp))
        },
        description = {
            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = VSTheme.shapes.large)
        },
        labels = {
            PlaceholderBox(
                modifier = Modifier
                    .width(72.dp)
                    .height(24.dp)
                    .clip(CircleShape))
            PlaceholderBox(
                modifier = Modifier
                    .width(64.dp)
                    .height(24.dp))
        }
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
    VSElevatedCard(
        modifier = modifier,
        onClick = onClick,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
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
}


@Composable
private fun PlaceholderBox(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape
) {
    Box(
        modifier = modifier
            .clip(shape)
            .placeholder(
                visible = true,
                color = VSTheme.colorScheme.onSurfaceVariant,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = VSTheme.colorScheme.onSurface
                )
            )
    )
}