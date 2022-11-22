package com.vanced.store.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.ui.component.BrowseAppCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun BrowseAppCardLoaded(
    onClick: () -> Unit,
    app: DomainBrowseApp,
    modifier: Modifier = Modifier,
) {
    BrowseAppCard(
        modifier = modifier,
        onClick = onClick,
        icon = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(VSTheme.colorScheme.secondary))
        },
        title = {
            Text(app.appName)
        },
        description = {
            Text(app.appDescription)
        },
        labels = {
            if (app.supportsNonroot) {
                Label {
                    Text(text = "Nonroot")
                }
            }
            if (app.supportsRoot) {
                Label {
                    Text(text = "Root")
                }
            }
        },
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
                .fillMaxSize()
                .background(VSTheme.colorScheme.secondary))
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
        onClick = null
    )
}