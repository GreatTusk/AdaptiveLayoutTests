package com.f776.adaptivelayouttests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.enums.EnumEntries

@Composable
internal fun RefriappNavigationDrawer(
    railItems: EnumEntries<AppDestinations>,
    currentTab: AppDestinations,
    onTabClicked: (AppDestinations) -> Unit,
) {
    PermanentDrawerSheet(modifier = Modifier.width(260.dp)) {
        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            RefriappNavigationDrawerHeader(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 8.dp
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            railItems.forEach {
                NavigationDrawerItem(
                    label = { Text(text = stringResource(it.label)) },
                    selected = it == currentTab,
                    onClick = { onTabClicked(it) },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun RefriappNavigationDrawerHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleSmall,
                fontStyle = FontStyle.Italic
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )
    }
}
