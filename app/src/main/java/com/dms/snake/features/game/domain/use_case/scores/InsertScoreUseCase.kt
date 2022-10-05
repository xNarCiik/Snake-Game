package com.dms.snake.features.game.domain.use_case.scores

import com.dms.snake.features.game.domain.model.Score
import com.dms.snake.features.game.domain.repository.SnakeRepository

class InsertScoreUseCase(
    private val snakeRepository: SnakeRepository
) {

    suspend operator fun invoke(score: Score) {
        snakeRepository.saveScore(score)
    }
}