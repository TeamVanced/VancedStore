package com.vanced.store.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.component.BrowseAppCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun BrowseAppCardLoaded(
    onDetailsClick: () -> Unit,
    appName: String,
    appDescription: String,
    supportsRoot: Boolean,
    supportsNonroot: Boolean,
    modifier: Modifier = Modifier,
) {
    BrowseAppCard(
        modifier = modifier,
        icon = {
            Box(modifier = Modifier
                .size(48.dp)
                .background(VSTheme.colorScheme.primary))
        },
        title = {
            Text(appName)
        },
        description = {
            Text(appDescription)
        },
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
        actions = {
            Button(onClick = onDetailsClick) {
                Text("Details")
            }
        }
    )
}

@Composable
fun BrowseAppCardLoading(
    modifier: Modifier = Modifier,
) {
    BrowseAppCard(
        modifier = modifier,
        icon = {
            Box(modifier = Modifier
                .size(48.dp)
                .clip(VSTheme.shapes.medium)
                .background(VSTheme.colorScheme.primary))
        },
        title = {
            Box(modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(24.dp)
                .clip(VSTheme.shapes.medium)
                .background(VSTheme.colorScheme.primary))
        },
        description = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .clip(VSTheme.shapes.medium)
                .background(VSTheme.colorScheme.primary))
        },
        labels = {
            Label(modifier = Modifier
                .width(48.dp)
                .height(24.dp)) {}
            Label(modifier = Modifier
                .width(36.dp)
                .height(24.dp)) {}
        },
        actions = {
            Box(modifier = Modifier
                .width(72.dp)
                .height(36.dp)
                .clip(CircleShape)
                .background(VSTheme.colorScheme.primary))
        }
    )
}