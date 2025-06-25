package dev.georgiys.changerrgb.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    getConnect: () -> Unit,
    getDeviceLise: () -> Unit
) {
    LaunchedEffect(Unit) {
        getConnect()
        getDeviceLise()
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.refreshing,
        onRefresh = {
            getConnect()
            getDeviceLise()
        }
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(
                uiState.deviceList,
            ) {
                Text(text = it.toString())
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.refreshing,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}