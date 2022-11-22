package com.vanced.store.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.vanced.store.ui.theme.VSTheme

@Composable
fun BrowseAppCard(
    onClick: (() -> Unit)?,
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    labels: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    VSElevatedCard(
        onClick = onClick,
        modifier = modifier,
        shape = VSTheme.shapes.large
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(VSTheme.shapes.medium)
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
            ) {
                icon()
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(VSTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.small)
            ) {
                ProvideTextStyle(VSTheme.typography.titleMedium) {
                    title()
                }
                ProvideTextStyle(VSTheme.typography.bodySmall) {
                    description()
                }
                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.extraSmall)
                ) {
                    labels()
                }
            }
        }
    }
}