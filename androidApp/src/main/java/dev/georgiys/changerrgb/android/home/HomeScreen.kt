package dev.georgiys.changerrgb.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.georgiys.changerrgb.android.home.view.ErrorSnackbar
import dev.georgiys.changerrgb.android.home.view.item.DeviceItem
import dev.georgiys.changerrgb.android.home.viewstate.HomeScreenState
import dev.georgiys.changerrgb.data.data.Device

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    getConnect: () -> Unit,
    getDeviceList: () -> Unit,
    onDeviceClick: (Device) -> Unit
) {
    LaunchedEffect(Unit) {
        getDeviceList()
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.refreshing,
        onRefresh = {
            getDeviceList()
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
            items(uiState.deviceList) { device ->
                DeviceItem(
                    device = device,
                    onClick = { onDeviceClick(device) }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.refreshing,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
        if (uiState.loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage?.let { error ->
            ErrorSnackbar(
                message = error,
                onRetry = getDeviceList,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}