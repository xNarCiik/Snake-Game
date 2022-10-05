package com.dms.snake.features.game.presentation.game

import androidx.compose.ui.unit.IntSize
import com.dms.snake.features.game.domain.util.SnakeOrientation

sealed class GameEvent {
    data class Start(val screenSize: IntSize) : GameEvent()
    object Restart : GameEvent()
    data class Pause(val paused: Boolean) : GameEvent()
    data class ChangeSnakeOrientation(val orientation: SnakeOrientation) : GameEvent()
}

