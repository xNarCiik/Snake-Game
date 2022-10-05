package com.dms.snake.features.game.domain.use_case.game

import com.dms.snake.features.game.domain.util.SnakeOrientation

class IsAllowToUpdateOrientationUseCase {

    operator fun invoke(
        currentOrientation: SnakeOrientation,
        newOrientation: SnakeOrientation
    ): Boolean {
        var allowUpdate = true
        when (currentOrientation) {
            SnakeOrientation.UP -> if (newOrientation == SnakeOrientation.DOWN) allowUpdate =
                false
            SnakeOrientation.DOWN -> if (newOrientation == SnakeOrientation.UP) allowUpdate =
                false
            SnakeOrientation.LEFT -> if (newOrientation == SnakeOrientation.RIGHT) allowUpdate =
                false
            SnakeOrientation.RIGHT -> if (newOrientation == SnakeOrientation.LEFT) allowUpdate =
                false
        }
        return allowUpdate
    }

}