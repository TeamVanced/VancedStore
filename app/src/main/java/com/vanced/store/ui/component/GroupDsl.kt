package com.vanced.store.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.theme.VSTheme

private val GroupRowContainerHeight = 48.dp
private val GroupRowItemMinWidth = 56.dp

@Composable
fun GroupRow(
    modifier: Modifier = Modifier,
    content: GroupListScope.() -> Unit
) {
    val itemScope = remember { GroupItemScopeImpl() }
    val latestContent by rememberUpdatedState(content)
    val items by remember {
        derivedStateOf {
            val listScope = GroupListScopeImpl()
            listScope.apply(latestContent)
            listScope.intervals
        }
    }
    VSOutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .height(GroupRowContainerHeight)
                .widthIn(min = GroupRowItemMinWidth),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, content ->
                if (index != 0) {
                    //Divider
                    Box(
                        modifier = Modifier
                            .background(color = VSTheme.colorScheme.outline)
                            .width(1.dp)
                            .height(GroupRowContainerHeight)
                    )
                }
                content.content(itemScope)()
            }
        }
    }
}

@Suppress("unused")
@Composable
fun GroupItemScope.ToggleItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    val containerColor by animateColorAsState(
        if (selected)
            VSTheme.colorScheme.primaryContainer
        else
            VSTheme.colorScheme.surface
    )
    Surface(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = RectangleShape,
        color = containerColor
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = GroupRowItemMinWidth)
                .height(GroupRowContainerHeight)
                .padding(horizontal = VSTheme.spacing.medium),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}

interface GroupListScope {

    fun item(
        content: @Composable GroupItemScope.() -> Unit
    )

    fun items(
        count: Int,
        itemContent: @Composable GroupItemScope.(index: Int) -> Unit
    )

}

class GroupListScopeImpl : GroupListScope {

    private val _intervals = mutableListOf<DividedItemIntervalContent>()
    val intervals: List<DividedItemIntervalContent> = _intervals

    override fun item(
        content: @Composable GroupItemScope.() -> Unit
    ) {
        _intervals.add(
            DividedItemIntervalContent(
                content = { @Composable { content() } }
            )
        )
    }

    override fun items(
        count: Int,
        itemContent: @Composable GroupItemScope.(index: Int) -> Unit
    ) {
        repeat(count) {
            _intervals.add(
                DividedItemIntervalContent(
                    content = { @Composable { itemContent(it) } }
                )
            )
        }
    }
}

inline fun <T> GroupListScope.items(
    items: Array<T>,
    crossinline itemContent: @Composable GroupItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

inline fun <T> GroupListScope.items(
    items: List<T>,
    crossinline itemContent: @Composable GroupItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

interface GroupItemScope
class GroupItemScopeImpl : GroupItemScope

class DividedItemIntervalContent(
    val content: GroupItemScope.() -> @Composable () -> Unit
)