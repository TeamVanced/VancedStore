package com.vanced.store.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vanced.store.R
import com.vanced.store.ui.component.Preference
import com.vanced.store.ui.navigation.VSNavigationScreen
import com.vanced.store.ui.navigation.VSNavigator
import com.vanced.store.ui.widget.CardLazyColumn
import com.vanced.store.ui.widget.ScreenScaffold
import com.vanced.store.ui.widget.ScreenTopAppBar

@Composable
fun MoreScreen(
    navigator: VSNavigator,
    modifier: Modifier = Modifier,
) {
    MoreScreen(
        onRepositoriesClick = { navigator.navigate(VSNavigationScreen.Repositories) },
        onThemesClick = { navigator.navigate(VSNavigationScreen.Themes) },
        modifier = modifier,
    )
}

@Composable
fun MoreScreen(
    onRepositoriesClick: () -> Unit,
    onThemesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        CardLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                RepositoriesItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    onClick = onRepositoriesClick
                )
            }
            item {
                ThemesItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    onClick = onThemesClick
                )
            }
        }
    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    ScreenTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = stringResource(R.string.navigation_more)
    )
}

@Composable
private fun RepositoriesItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Preference(
        modifier = modifier,
        onClick = onClick,
        title = { Text(stringResource(R.string.navigation_repositories)) },
        trailing = {
            Icon(
                painter = painterResource(R.drawable.ic_navigate_next),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun ThemesItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Preference(
        modifier = modifier,
        onClick = onClick,
        title = { Text(stringResource(R.string.navigation_themes)) },
        trailing = {
            Icon(
                painter = painterResource(R.drawable.ic_navigate_next),
                contentDescription = null
            )
        }
    )
}