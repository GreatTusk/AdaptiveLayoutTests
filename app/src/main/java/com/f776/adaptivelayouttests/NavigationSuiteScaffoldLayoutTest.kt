package com.f776.adaptivelayouttests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.f776.adaptivelayouttests.ui.theme.AdaptiveLayoutTestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationSuiteLayout(modifier: Modifier = Modifier) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val navigationSuiteType = with(adaptiveInfo) {
        if (
            windowPosture.isTabletop ||
            windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT
        ) {
            NavigationSuiteType.NavigationRail
        } else if (
            windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM
        ) {
            NavigationSuiteType.NavigationRail
        } else if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteType.NavigationBar
        }
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    NavigationSuiteScaffoldLayout(
        navigationSuite = {
            when (navigationSuiteType) {
                NavigationSuiteType.NavigationDrawer -> RefriappNavigationDrawer(
                    railItems = AppDestinations.entries,
                    currentTab = currentDestination,
                    onTabClicked = { currentDestination = it }
                )

                NavigationSuiteType.NavigationRail -> RefriappNavigationRail(
                    railItems = AppDestinations.entries,
                    currentTab = currentDestination,
                    onTabClicked = { currentDestination = it },
                    onBackPressed = {},
                    modifier = Modifier.displayCutoutPadding()
                )

                else -> NavigationSuite {
                    AppDestinations.entries.forEach {
                        item(
                            icon = {
                                Icon(
                                    it.icon,
                                    contentDescription = stringResource(it.contentDescription)
                                )
                            },
                            label = { Text(stringResource(it.label)) },
                            selected = it == currentDestination,
                            onClick = { currentDestination = it }
                        )
                    }
                }
            }
        },
        layoutType = navigationSuiteType
    ) {
        Scaffold(
            topBar = {
                if (navigationSuiteType == NavigationSuiteType.NavigationBar) {
                    RefriappTopBar(
                        canGoBack = false,
                        onBackPressed = {},
                        scrollBehavior = scrollBehavior
                    )
                }
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { innerPadding ->
            SampleLazyColumn(innerPadding, location = currentDestination.name)
        }

    }
}

@Composable
fun SampleLazyColumn(paddingValues: PaddingValues = PaddingValues(0.dp), location: String) {
    val itemsList = List(22) { "$location Item #$it" }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Adaptive(minSize = 200.dp)
    ) {
        items(itemsList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showBackground = true, showSystemUi = true)
@Preview(
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showBackground = true,
    showSystemUi = true
)
@Preview(device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Preview(device = "spec:width=673dp,height=841dp", showBackground = true)
@Composable
private fun NavigationSuitePreview() {
    AdaptiveLayoutTestsTheme {
        NavigationSuiteLayout()
    }
}