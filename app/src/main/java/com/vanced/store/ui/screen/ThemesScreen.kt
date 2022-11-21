package com.vanced.store.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vanced.store.R
import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.ui.component.SegmentedButton
import com.vanced.store.ui.component.SegmentedButtonRow
import com.vanced.store.ui.component.VSOutlinedCard
import com.vanced.store.ui.navigation.VSNavigator
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.ThemesViewModel
import com.vanced.store.ui.widget.CardLazyVerticalGrid
import com.vanced.store.ui.widget.ScreenScaffold
import com.vanced.store.ui.widget.ScreenTopAppBar
import org.koin.androidx.compose.getViewModel

@Composable
fun ThemesScreen(
    navigator: VSNavigator,
    modifier: Modifier = Modifier,
    viewModel: ThemesViewModel = getViewModel(),
) {
    ThemesScreen(
        onBackClick = navigator::back,
        onThemeChange = viewModel::updateTheme,
        onAccentChange = viewModel::updateAccent,
        currentTheme = viewModel.theme,
        currentAccent = viewModel.accent,
        modifier = modifier,
    )
}

@Composable
fun ThemesScreen(
    onBackClick: () -> Unit,
    onThemeChange: (AppTheme) -> Unit,
    onAccentChange: (AppAccent) -> Unit,
    currentTheme: AppTheme,
    currentAccent: AppAccent,
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
                SegmentedButtonRow {
                    for (theme in AppTheme.values()) {
                        SegmentedButton(
                            selected = currentTheme == theme,
                            onClick = { onThemeChange(theme) }
                        ) {
                            Text(theme.name)
                        }
                    }
                }
            }
            items(enumValues<AppAccent>()) { accent ->
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
    accent: AppAccent,
    theme: AppTheme,
    modifier: Modifier = Modifier,
) {
    VSTheme(
        theme = theme,
        accent = accent,
        canUseDynamic = false
    ) {
        val cardBackground = if (selected) VSTheme.colorScheme.secondaryContainer else VSTheme.colorScheme.surface
        val cardListItem = if (selected) VSTheme.colorScheme.surface else VSTheme.colorScheme.secondaryContainer
        VSOutlinedCard(
            modifier = modifier,
            onClick = onClick,
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.outlinedCardColors(
                containerColor = cardBackground
            )
        ) {
            Column(
                modifier = Modifier.padding(VSTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(3) { index ->
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = cardListItem,
                            shape = VSTheme.shapes.large
                        ) {
                            Row(
                                modifier = Modifier.padding(VSTheme.spacing.medium),
                                horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(VSTheme.shapes.medium)
                                        .background(LocalContentColor.current)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(
                                            fraction = when (index) {
                                                0 -> 0.8f
                                                1 -> 0.6f
                                                else -> 0.7f
                                            }
                                        )
                                        .height(20.dp)
                                        .clip(VSTheme.shapes.large)
                                        .background(LocalContentColor.current)
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = selected) {
                        Row {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(R.drawable.ic_check),
                                contentDescription = null
                            )
                            Spacer(Modifier.width(4.dp))
                        }
                    }
                    Text(name)
                }
            }
        }
    }
}