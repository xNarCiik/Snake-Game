package com.example.sampleapp.feature_game.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.feature_game.presentation.game.GameViewModel
import com.example.sampleapp.feature_game.presentation.game.components.GameScreen
import com.example.sampleapp.feature_game.presentation.highscores.components.HighscoresScreen
import com.example.sampleapp.feature_game.presentation.menu.components.MenuScreen
import com.example.sampleapp.feature_game.presentation.util.Screen
import com.example.sampleapp.ui.theme.SampleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val gameViewModel : GameViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MenuScreen.route
                    ) {
                        composable(route = Screen.MenuScreen.route) {
                            MenuScreen(navController, gameViewModel)
                        }
                        composable(route = Screen.HighscoresScreen.route) {
                            HighscoresScreen(navController)
                        }
                        composable(route = Screen.GameScreen.route) {
                            GameScreen(navController, gameViewModel)
                        }
                    }
                }
            }
        }
    }
}