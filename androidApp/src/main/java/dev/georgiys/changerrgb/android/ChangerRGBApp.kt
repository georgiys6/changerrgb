package dev.georgiys.changerrgb.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.georgiys.changerrgb.android.common.ChangerAppBar
import dev.georgiys.changerrgb.android.common.Home
import dev.georgiys.changerrgb.android.common.changerDestinations
import dev.georgiys.changerrgb.android.home.HomeScreen
import dev.georgiys.changerrgb.android.home.HomeViewModel
import dev.georgiys.changerrgb.android.ui.ShowResult
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangerRGBApp(){
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark){
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = changerDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    }?: Home

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ChangerAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ){
                navController.navigateUp()
            }
        }
    ) { innerPaddings ->
        NavHost (
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs,
        ){
            composable(Home.routeWithArgs){
                val homeViewModel: HomeViewModel = koinViewModel()
                val uiState by remember { derivedStateOf { homeViewModel.uiState } }
                val isSuccess by  homeViewModel.isSuccess.collectAsState()
                ShowResult(
                    isSuccess,
                    snackBarHostState = scaffoldState.snackbarHostState
                ){
                    homeViewModel.resetSuccess()
                }
                HomeScreen(
                    uiState = uiState,
                    getConnect = {
//                        homeViewModel.getConnect()
                        homeViewModel.fakeGetConnect()
//                        navController.navigate(
//                            "${Detail.route}/${it.id}"
//                        )
                    },
                    getDeviceLise = {
                        homeViewModel.getFakeDeviceList()
                    }
                )
            }
        }
    }

}