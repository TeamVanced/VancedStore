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
import com.vanced.store.db.entity.Repository
import com.vanced.store.ui.component.VSOutlinedTextField
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.RepositoriesViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Composable
fun RepositoriesScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: RepositoriesViewModel = getViewModel()
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
        floatingActionButton = {
            FloatingActionButton(onClick = { addDialogVisible = true }) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
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
            onSave = { name, endpoint ->
                viewModel.saveRepo(name, endpoint)
                addDialogVisible = false
            }
        )
    }
}

@Composable
private fun Body(
    state: RepositoriesViewModel.State,
    onRepositoryRemove: (Repository) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        transitionSpec = { fadeIn() with  fadeOut() }
    ) { animatedState ->
        when (animatedState) {
            is RepositoriesViewModel.State.Loading -> {
                ScreenLoading(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is RepositoriesViewModel.State.Loaded -> {
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
    repositories: List<Repository>,
    onRepositoryRemove: (Repository) -> Unit,
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
fun AddDialog(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onSave: (name: String, endpoint: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("") }
    var endpoint by remember { mutableStateOf("") }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                enabled = name.isNotEmpty() && endpoint.isNotEmpty(),
                onClick = { onSave(name, endpoint) },
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
                verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VSOutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.repositories_add_label_name)) }
                )
                VSOutlinedTextField(
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