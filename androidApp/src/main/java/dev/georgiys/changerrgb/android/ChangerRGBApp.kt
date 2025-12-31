package dev.georgiys.changerrgb.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.georgiys.changerrgb.android.common.ChangerAppBar
import dev.georgiys.changerrgb.android.common.Home
import dev.georgiys.changerrgb.android.common.changerDestinations
import dev.georgiys.changerrgb.android.home.HomeScreen
import dev.georgiys.changerrgb.android.home.HomeUiEvent
import dev.georgiys.changerrgb.android.home.HomeViewModel
import dev.georgiys.changerrgb.android.home.view.BottomAppBar
import dev.georgiys.changerrgb.android.home.view.Screen
import dev.georgiys.changerrgb.android.itemchip.ItemChipScreen
import dev.georgiys.changerrgb.android.itemchip.ItemChipViewModel
import dev.georgiys.changerrgb.android.itemchip.telemetry.AxisScreen
import dev.georgiys.changerrgb.android.itemchip.telemetry.AxisViewModel
import dev.georgiys.changerrgb.android.settings.SettingsScreen
import dev.georgiys.changerrgb.android.settings.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

const val ITEM_CHIP_ROUTE = "item_chip/{chipType}/{chipId}"

@Composable
fun ChangerRGBApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
    SideEffect { systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark) }

    // Создаём SettingsViewModel ОДИН РАЗ здесь и передаём в SettingsScreen
    val settingsVm: SettingsViewModel = koinViewModel()



    // Подписываемся на state MVI
    val settingsState by settingsVm.state.collectAsState()

    // вспомогательная проверка: пустые ли настройки
    val isSettingsEmpty = remember(settingsState) {
        settingsState.url.isBlank() || settingsState.login.isBlank() || settingsState.password.isBlank()
    }

    var currentItem by remember {
        mutableStateOf(if (isSettingsEmpty) Screen.Settings else Screen.Home)
    }

    LaunchedEffect(settingsState.isSaved, isSettingsEmpty) {
        if (isSettingsEmpty) {
            currentItem = Screen.Settings
            navController.navigate("settings") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            if (settingsState.isSaved) {
                currentItem = Screen.Home
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = changerDestinations.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ChangerAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) { navController.navigateUp() }
        },
        bottomBar = {
            BottomAppBar(
                currentItem = currentItem,
                onScreenSelected = { screen ->
                    currentItem = screen
                    when (screen) {
                        Screen.Home -> navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                        Screen.Settings -> navController.navigate("settings") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = "home"
        ) {
            composable("home") {
                val homeViewModel: HomeViewModel = koinViewModel()
                val uiState by homeViewModel.uiState.collectAsState()

                LaunchedEffect(Unit) {
                    homeViewModel.event.collect { event ->
                        when (event) {
                            is HomeUiEvent.OpenItemChip -> {
                                navController.navigate("item_chip/${event.chipType}/${event.chipId}")
                            }
                            is HomeUiEvent.OpenItemAxis -> {
                                navController.navigate("axis")
                            }
                        }
                    }
                }

                HomeScreen(
                    uiState = uiState,
                    getConnect = homeViewModel::getConnect,
                    getDeviceList = homeViewModel::getDeviceList,
                    onDeviceClick = homeViewModel::onDeviceClicked
                )
            }

            composable("settings") {
                SettingsScreen(vm = settingsVm)
            }
            composable(
                route = ITEM_CHIP_ROUTE,
                arguments = listOf(
                    navArgument("chipType") { type = NavType.StringType },
                    navArgument("chipId") { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val itemChipViewModel: ItemChipViewModel =
                    koinViewModel(viewModelStoreOwner = backStackEntry)
                val uiState by itemChipViewModel.uiState.collectAsState()
                ItemChipScreen(
                    uiState,
                    itemChipViewModel::setStateToLed,
                    itemChipViewModel::setSpeedToLed,
                    itemChipViewModel::setBrightnessToLed,
                    itemChipViewModel::onAxisClick
                )
                LaunchedEffect(Unit) {
                    itemChipViewModel.event.collect { event ->
                        when (event) {
                            is HomeUiEvent.OpenItemAxis -> {
                                navController.navigate("axis/${event.chipId}/${event.typeDevice}")
                            }
                            else -> {

                            }

                        }
                    }
                }
            }
            composable(
                route = "axis/{chipId}/{typeDevice}",
                arguments = listOf(
                    navArgument("chipId") { type = NavType.LongType },
                    navArgument("typeDevice") { type = NavType.StringType },
                )
            ) { backStackEntry ->

                val viewModel: AxisViewModel =
                    koinViewModel(viewModelStoreOwner = backStackEntry)

                val uiState by viewModel.uiState.collectAsState()

                AxisScreen(
                    state = uiState
                )
            }
        }
    }
}
