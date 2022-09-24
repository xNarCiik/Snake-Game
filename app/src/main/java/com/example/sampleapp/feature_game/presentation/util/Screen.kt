package com.example.sampleapp.feature_game.presentation.util

sealed class Screen(val route: String) {
    object MenuScreen: Screen("menu_screen")
    object HighscoresScreen: Screen("highscores_screen")
    object GameScreen: Screen("game_screen")
}
