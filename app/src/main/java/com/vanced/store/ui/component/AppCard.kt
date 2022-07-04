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
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    labels: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    VSElevatedCard(
        modifier = modifier,
        shape = VSTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(VSTheme.spacing.extraLarge)
        ) {
            Row(
                modifier = Modifier.align(Alignment.TopEnd),
                horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.extraSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                labels()
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.large)
            ) {
                Box(modifier = Modifier.clip(VSTheme.shapes.medium)) {
                    icon()
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = VSTheme.spacing.extraSmall),
                    verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium)
                ) {
                    ProvideTextStyle(VSTheme.typography.titleMedium) {
                        title()
                    }
                    ProvideTextStyle(VSTheme.typography.bodySmall) {
                        description()
                    }
                    Row(
                        modifier = Modifier.align(Alignment.End),
                        horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.small)
                    ) {
                        actions()
                    }
                }
            }
        }
    }
}