package com.vanced.store.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.db.entity.AppsRepositoryEntity
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.viewmodel.RepositoriesViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Composable
fun RepositoriesScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: RepositoriesViewModel = getViewModel()
    val state = viewModel.state
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
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
                is RepositoriesViewModel.State.Loading -> {
                    RepositoriesScreenLoading(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is RepositoriesViewModel.State.Loaded -> {
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
            RepositoryCardLoading(modifier = Modifier.fillParentMaxWidth())
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
                modifier = Modifier.fillParentMaxWidth(),
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
    ScreenTopAppBar(
        modifier = modifier,
        title = stringResource(R.string.navigation_repositories),
        scrollBehavior = scrollBehavior
    )
}