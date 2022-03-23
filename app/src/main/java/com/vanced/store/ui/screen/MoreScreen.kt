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
import com.vanced.store.ui.widget.CardLazyColumn
import com.vanced.store.ui.widget.ScreenScaffold
import com.vanced.store.ui.widget.ScreenTopAppBar

@Composable
fun MoreScreen(
    modifier: Modifier = Modifier,
    onRepositoriesClick: () -> Unit,
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