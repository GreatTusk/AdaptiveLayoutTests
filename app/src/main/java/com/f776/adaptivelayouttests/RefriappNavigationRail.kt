package com.f776.adaptivelayouttests

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.enums.EnumEntries

@Composable
internal fun RefriappNavigationRail(
    modifier: Modifier = Modifier,
    railItems: EnumEntries<AppDestinations>,
    canGoBack: Boolean = false,
    currentTab: AppDestinations,
    onBackPressed: () -> Unit,
    onTabClicked: (AppDestinations) -> Unit
) {
    NavigationRail(
        header = {
            if (canGoBack) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = if (!canGoBack) 12.dp else 0.dp)
                    .size(36.dp)
            )
        },
        modifier = modifier
    ) {
        railItems.forEach { item ->
            NavigationRailItem(
                selected = currentTab == item,
                onClick = { onTabClicked(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = if (item == currentTab) MaterialTheme.colorScheme.primary else LocalContentColor.current
                    )
                },
                label = { Text(text = stringResource(item.label)) },
                alwaysShowLabel = true,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {/* TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.app_name)
            )
        }
    }
}
