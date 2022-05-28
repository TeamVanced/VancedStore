package com.vanced.store.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.vanced.store.R
import com.vanced.store.domain.model.DomainRepo
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.RepoViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Composable
fun RepositoriesScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RepoViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    var addDialogVisible by remember { mutableStateOf(false) }
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick
            )
        },
        floatingActionButton = { FAB(onClick = { addDialogVisible = true }) }
    ) { paddingValues ->
        Body(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = state,
            onRepositoryRemove = { repository ->
                viewModel.deleteRepo(repository)
            }
        )
    }
    if (addDialogVisible) {
        AddDialog(
            onDismissRequest = { addDialogVisible = false },
            onCancel = { addDialogVisible = false },
            onSave = { endpoint ->
                viewModel.saveRepo(endpoint)
                addDialogVisible = false
            }
        )
    }
}

@Composable
private fun Body(
    state: RepoViewModel.State,
    onRepositoryRemove: (endpoint: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        transitionSpec = {
            fadeIn() with fadeOut()
        }
    ) { animatedState ->
        when (animatedState) {
            is RepoViewModel.State.Loading -> {
                ScreenLoading(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is RepoViewModel.State.Loaded -> {
                ScreenLoaded(
                    modifier = Modifier
                        .fillMaxSize(),
                    repositories = animatedState.repositories,
                    onRepositoryRemove = onRepositoryRemove
                )
            }
        }
    }
}

@Composable
private fun ScreenLoading(
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
private fun ScreenLoaded(
    repositories: List<DomainRepo>,
    onRepositoryRemove: (endpoint: String) -> Unit,
    modifier: Modifier = Modifier
) {
    CardLazyColumn(modifier = modifier) {
        items(repositories) { repository ->
            RepositoryCardLoaded(
                modifier = Modifier.fillParentMaxWidth(),
                onRemove = { onRepositoryRemove(repository.endpoint) },
                name = repository.name
            )
        }
    }
}

@Composable
private fun AddDialog(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onSave: (endpoint: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var endpoint by remember { mutableStateOf("") }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                enabled = endpoint.isNotEmpty(),
                onClick = { onSave(endpoint) },
            ) {
                Text(stringResource(R.string.dialog_save))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(stringResource(R.string.dialog_cancel))
            }
        },
        title = { Text(stringResource(R.string.repositories_add_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = endpoint,
                    onValueChange = { endpoint = it },
                    label = { Text(stringResource(R.string.repositories_add_label_endpoint)) }
                )
            }
        }
    )
}

@Composable
private fun AppBar(
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    ScreenTopAppBar(
        modifier = modifier,
        title = stringResource(R.string.navigation_repositories),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_navigate_before),
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun FAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null
        )
    }
}