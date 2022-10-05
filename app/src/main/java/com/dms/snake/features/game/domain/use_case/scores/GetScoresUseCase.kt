package com.dms.snake.features.game.domain.use_case.scores

import com.dms.snake.features.game.domain.model.Score
import com.dms.snake.features.game.domain.repository.SnakeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScoresUseCase(
    private val snakeRepository: SnakeRepository
) {

    operator fun invoke(): Flow<List<Score>> {
        return snakeRepository.getScores().map { scores ->
            scores.sortedBy { it.score }.subList(0, 10)
        }
    }
}