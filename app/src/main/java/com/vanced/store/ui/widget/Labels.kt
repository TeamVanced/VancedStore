package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.vanced.store.ui.component.VSOutlinedCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun Label(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStartPercent = 50,
            topEndPercent = 50,
            bottomStartPercent = 50,
            bottomEndPercent = 15
        ),
        color = VSTheme.colorScheme.tertiaryContainer
    ) {
        ProvideTextStyle(VSTheme.typography.labelSmall) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = VSTheme.spacing.medium,
                        vertical = VSTheme.spacing.small
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}