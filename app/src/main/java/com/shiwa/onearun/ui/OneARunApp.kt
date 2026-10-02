package com.shiwa.onearun.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shiwa.onearun.ui.screens.EndScreen
import com.shiwa.onearun.ui.screens.HomeScreen
import com.shiwa.onearun.ui.screens.LobbyScreen
import com.shiwa.onearun.ui.screens.RunningScreen
import com.shiwa.onearun.ui.screens.StartRaceScreen
import com.shiwa.onearun.ui.screens.viewmodel.HomeViewModel

enum class AppScreen {
    Home,
    Lobby,
    Race,
    EnterRace,
    StartRace,
    End
}

@Composable
fun OneARunApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(
                    viewModel = homeViewModel,
                    onStartRaceNavigate = {
                        navController.navigate(AppScreen.StartRace.name)
                    },
                    onEnterRaceNavigate = {
                        navController.navigate(AppScreen.EnterRace.name)
                    }
                )
            }

            composable(route = AppScreen.StartRace.name) {
                StartRaceScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(route = AppScreen.EnterRace.name) {
                LobbyScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(route = AppScreen.Lobby.name) {
                LobbyScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(route = AppScreen.Race.name) {
                RunningScreen(
                    onNavigateBack = {
                        navController.navigate(AppScreen.End.name)
                    }
                )
            }

            composable(route = AppScreen.End.name) {
                EndScreen(
                    onNavigateHome = {
                        navController.popBackStack(AppScreen.Home.name, inclusive = false)
                    }
                )
            }
        }
    }
}
