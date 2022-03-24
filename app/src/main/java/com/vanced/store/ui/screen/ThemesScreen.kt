package com.vanced.store.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vanced.store.R
import com.vanced.store.domain.manager.ApplicationAccent
import com.vanced.store.domain.manager.ApplicationTheme
import com.vanced.store.ui.component.GroupRow
import com.vanced.store.ui.component.ToggleItem
import com.vanced.store.ui.component.VSOutlinedCard
import com.vanced.store.ui.component.items
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.widget.CardLazyVerticalGrid
import com.vanced.store.ui.widget.ScreenScaffold
import com.vanced.store.ui.widget.ScreenTopAppBar

@Composable
fun ThemesScreen(
    onBackClick: () -> Unit,
    onThemeChange: (ApplicationTheme) -> Unit,
    onAccentChange: (ApplicationAccent) -> Unit,
    currentTheme: ApplicationTheme,
    currentAccent: ApplicationAccent,
    modifier: Modifier = Modifier,
) {
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            TopBar(
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        CardLazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item(span = {
                GridItemSpan(maxCurrentLineSpan)
            }) {
                Box(
                    contentAlignment = Alignment.CenterEnd
                ) {
                    GroupRow {
                        items(enumValues<ApplicationTheme>()) { theme ->
                            ToggleItem(
                                selected = currentTheme == theme,
                                onClick = { onThemeChange(theme) }
                            ) {
                                Text(theme.name)
                            }
                        }
                    }
                }
            }
            items(enumValues<ApplicationAccent>()) { accent ->
                AccentCard(
                    onClick = { onAccentChange(accent) },
                    name = accent.name,
                    selected = currentAccent == accent,
                    accent = accent,
                    theme = currentTheme
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    ScreenTopAppBar(
        scrollBehavior = scrollBehavior,
        title = stringResource(R.string.navigation_themes),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_navigate_before),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun AccentCard(
    onClick: () -> Unit,
    name: String,
    selected: Boolean,
    accent: ApplicationAccent,
    theme: ApplicationTheme,
    modifier: Modifier = Modifier,
) {
    val containerColor by animateColorAsState(
        if (selected)
            VSTheme.colorScheme.primaryContainer
        else
            VSTheme.colorScheme.surface
    )
    val contentColor by animateColorAsState(contentColorFor(containerColor))

    VSOutlinedCard(
        modifier = modifier,
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(VSTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium)
        ) {
            VSTheme(theme = theme, accent = accent) {
                val boxColor by animateColorAsState(
                    if (selected)
                        VSTheme.colorScheme.onPrimaryContainer
                    else
                        VSTheme.colorScheme.primaryContainer
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .height(36.dp)
                                .fillMaxWidth()
                                .clip(VSTheme.shapes.medium)
                                .background(color = boxColor)
                        )
                    }
                }
            }
            Text(name)
        }
    }
}