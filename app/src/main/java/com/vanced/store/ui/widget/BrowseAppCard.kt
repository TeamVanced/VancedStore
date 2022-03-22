package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
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
import com.vanced.store.ui.component.VSCard
import com.vanced.store.ui.component.VSElevatedCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun LoadedListBrowseAppCard(
    appName: String,
    appDescription: String,
    supportsNonroot: Boolean,
    supportsRoot: Boolean,
    modifier: Modifier = Modifier,
) {
    BaseLoadedBrowseAppCard(
        modifier = modifier,
        onClick = {},
        details = {
            ListDetailsLayout(
                icon = {
                    VSCard(
                        modifier = Modifier.size(48.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {}
                },
                title = { Text(appName) },
                description = { Text(appDescription) }
            )
        },
        supportsNonroot = supportsNonroot,
        supportsRoot = supportsRoot
    )
}

@Composable
fun LoadedGridBrowseAppCard(
    appName: String,
    appDescription: String,
    supportsNonroot: Boolean,
    supportsRoot: Boolean,
    modifier: Modifier = Modifier,
) {
    BaseLoadedBrowseAppCard(
        modifier = modifier,
        onClick = {},
        details = {
            GridDetailsLayout(
                icon = {
                    VSCard(
                        modifier = Modifier.size(36.dp),
                        shape = CircleShape
                    ) {}
                },
                title = { Text(appName) },
                description = { Text(appDescription) }
            )
        },
        supportsNonroot = supportsNonroot,
        supportsRoot = supportsRoot
    )
}

@Composable
fun LoadingListBrowseAppCard(
    modifier: Modifier = Modifier
) {
    BaseLoadingBrowseAppCard(
        modifier = modifier,
        details = {
            ListDetailsLayout(
                icon = {
                    PlaceholderBox(
                        modifier = Modifier.size(48.dp),
                        shape = RoundedCornerShape(16.dp)
                    )
                },
                title = {
                    PlaceholderBox(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(24.dp)
                    )
                },
                description = {
                    PlaceholderBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = VSTheme.shapes.large
                    )
                }
            )
        }
    )
}

@Composable
fun LoadingGridBrowseAppCard(
    modifier: Modifier = Modifier
) {
    BaseLoadingBrowseAppCard(
        modifier = modifier,
        details = {
            GridDetailsLayout(
                icon = {
                    PlaceholderBox(
                        modifier = Modifier.size(36.dp),
                    )
                },
                title = {
                    PlaceholderBox(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(24.dp)
                    )
                },
                description = {
                    PlaceholderBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = VSTheme.shapes.large
                    )
                }
            )
        },
    )
}

@Composable
private fun BaseLoadedBrowseAppCard(
    onClick: () -> Unit,
    details: @Composable () -> Unit,
    supportsNonroot: Boolean,
    supportsRoot: Boolean,
    modifier: Modifier = Modifier,
) {
    BaseBrowseAppCard(
        modifier = modifier,
        details = details,
        labels = {
            if (supportsNonroot) {
                Label {
                    Text(text = "Nonroot")
                }
            }
            if (supportsRoot) {
                Label {
                    Text(text = "Root")
                }
            }
        },
        onClick = onClick
    )
}

@Composable
private fun BaseLoadingBrowseAppCard(
    details: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseBrowseAppCard(
        modifier = modifier,
        details = details,
        labels = {
            PlaceholderBox(
                modifier = Modifier
                    .width(72.dp)
                    .height(24.dp)
                    .clip(CircleShape)
            )
            PlaceholderBox(
                modifier = Modifier
                    .width(64.dp)
                    .height(24.dp)
            )
        },
        onClick = null
    )
}

@Composable
private fun BaseBrowseAppCard(
    details: @Composable () -> Unit,
    labels: @Composable () -> Unit,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
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
            details()
            Divider(
                modifier = Modifier.padding(
                    vertical = VSTheme.spacing.innerMedium
                ),
            )
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
private fun ListDetailsLayout(
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge)
    ) {
        icon()
        Column(
            modifier = Modifier.padding(top = VSTheme.spacing.innerSmall),
            verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerMedium)
        ) {
            ProvideTextStyle(VSTheme.typography.titleMedium) {
                title()
            }
            ProvideTextStyle(VSTheme.typography.bodyMedium) {
                description()
            }
        }
    }
}

@Composable
private fun GridDetailsLayout(
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge)
        ) {
            icon()
            ProvideTextStyle(VSTheme.typography.titleMedium) {
                title()
            }
        }
        ProvideTextStyle(VSTheme.typography.bodyMedium) {
            description()
        }
    }
}