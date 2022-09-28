package com.dms.snake.features.game.domain.use_case

import androidx.compose.ui.unit.IntSize
import com.dms.snake.features.game.domain.model.Point
import com.dms.snake.features.game.presentation.game.FoodState
import com.dms.snake.features.game.presentation.game.SnakeState

class GenerateFoodStateUseCase {

    operator fun invoke(
        screenSize: IntSize
    ): FoodState {
        val x =
            SnakeState.SIZE_SNAKE * (1 until (screenSize.width / SnakeState.SIZE_SNAKE).toInt()).random()
        val y =
            SnakeState.SIZE_SNAKE * (1 until (screenSize.height / SnakeState.SIZE_SNAKE).toInt()).random()
        return FoodState(Point(x, y))
    }

}