package com.dms.snake.feature_game.presentation.game

import com.dms.snake.feature_game.domain.util.SnakeOrientation

sealed class GameEvent {
    data class Pause(val paused: Boolean): GameEvent()
    data class ChangeSnakeOrientation(val orientation: SnakeOrientation): GameEvent()
}

