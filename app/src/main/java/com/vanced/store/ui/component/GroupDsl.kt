package com.vanced.store.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    Surface(
        modifier = modifier
            .height(GroupRowContainerHeight)
            .widthIn(min = GroupRowItemMinWidth)
            .clip(RoundedCornerShape(12.dp)),
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, content ->
                if (index != 0) {
                    //Divider
                    Box(
                        modifier = Modifier
                            .background(color = VSTheme.colorScheme.surface)
                            .width(2.dp)
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
    val color by animateColorAsState(
        if (selected) VSTheme.colorScheme.secondaryContainer else Color.Transparent
    )
    Box(
        modifier = modifier
            .widthIn(min = GroupRowItemMinWidth)
            .height(GroupRowContainerHeight)
            .background(color)
            .clickable(onClick = onClick, enabled = enabled)
            .padding(horizontal = VSTheme.spacing.innerSmall),
        contentAlignment = Alignment.Center
    ) {
        icon()
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