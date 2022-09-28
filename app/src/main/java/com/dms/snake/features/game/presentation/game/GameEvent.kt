package com.dms.snake.features.game.presentation.game

import com.dms.snake.features.game.domain.util.SnakeOrientation

sealed class GameEvent {
    data class Pause(val paused: Boolean): GameEvent()
    data class ChangeSnakeOrientation(val orientation: SnakeOrientation): GameEvent()
}

