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
            val nbScores = scores.size
            if (nbScores >= 10) {
                scores.sortedByDescending { it.score }.subList(0, 10)
            } else {
                scores.sortedByDescending { it.score }.subList(0, nbScores)
            }
        }
    }
}