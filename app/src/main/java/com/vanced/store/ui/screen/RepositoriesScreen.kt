package com.vanced.store.ui.screen

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.db.entity.AppsRepositoryEntity
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.RepositoriesViewModel
import com.vanced.store.ui.widget.RepositoryCardLoaded
import org.koin.androidx.compose.getViewModel

private typealias StateLoading = RepositoriesViewModel.State.Loading
private typealias StateLoaded = RepositoriesViewModel.State.Loaded

@Composable
fun RepositoriesScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: RepositoriesViewModel = getViewModel()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    val state = viewModel.state
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    ) {
        VSSwipeRefresh(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = rememberSwipeRefreshState(
                isRefreshing = state.isLoading
            ),
            onRefresh = {
                viewModel.fetch()
            }
        ) {
            when (state) {
                is StateLoading -> {
                    RepositoriesScreenLoading(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is StateLoaded -> {
                    RepositoriesScreenLoaded(
                        modifier = Modifier.fillMaxSize(),
                        repositories = state.repositoryEntities,
                        onRepositoryRemove = { /*TODO*/ }
                    )
                }
            }
        }
    }
}

@Composable
fun RepositoriesScreenLoading(
    modifier: Modifier = Modifier,
) {
    CardLazyColumn(
        modifier = modifier,
        scrollEnabled = false
    ) {
        items(10) {
            RepositoriesScreenLoading()
        }
    }
}

@Composable
fun RepositoriesScreenLoaded(
    repositories: List<AppsRepositoryEntity>,
    onRepositoryRemove: (AppsRepositoryEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    CardLazyColumn(modifier = modifier) {
        items(repositories) { repository ->
            RepositoryCardLoaded(
                onRemove = { onRepositoryRemove(repository) },
                name = repository.name
            )
        }
    }
}

@Composable
private fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.navigation_repositories)) },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun CardLazyColumn(
    modifier: Modifier = Modifier,
    scrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerLarge),
        contentPadding = PaddingValues(VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
    )
}