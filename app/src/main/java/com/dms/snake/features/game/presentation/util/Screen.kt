package com.dms.snake.features.game.presentation.util

sealed class Screen(val route: String) {
    object MenuScreen: Screen("menu_screen")
    object HighscoresScreen: Screen("highscores_screen")
    object GameScreen: Screen("game_screen")
}
