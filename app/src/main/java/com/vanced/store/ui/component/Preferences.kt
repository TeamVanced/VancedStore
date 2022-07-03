package com.vanced.store.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.theme.VSTheme

@Composable
fun Preference(
    onClick: () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null,
) {
    VSElevatedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .heightIn(min = 48.dp)
                .padding(VSTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ProvideTextStyle(VSTheme.typography.titleSmall) {
                    title()
                }
            }
            if (trailing != null) {
                trailing()
            }
        }
    }
}