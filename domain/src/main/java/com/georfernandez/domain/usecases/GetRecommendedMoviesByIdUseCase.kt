package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetRecommendedMoviesByIdUseCase {
    suspend operator fun invoke(tMDBMovieId: Int): CoroutineResult<List<Movie>>
}

class GetRecommendedMoviesByIdUseCaseImpl @Inject constructor(
    private val tMDBService: TMDBService,
    private val tMDBRepository: TMDBRepository,
) : GetRecommendedMoviesByIdUseCase {
    override suspend fun invoke(tMDBMovieId: Int): CoroutineResult<List<Movie>> {
        return when (val serviceResult = tMDBService.getRecommendedMoviesById(tMDBMovieId)) {
            is CoroutineResult.Success -> {
                tMDBRepository.insertRecommendedMoviesToDB(tMDBMovieId, serviceResult.data)
                tMDBRepository.getDBGetRecommendedMoviesById(tMDBMovieId)
            }

            is CoroutineResult.Failure -> {
                tMDBRepository.getDBGetRecommendedMoviesById(tMDBMovieId)
            }
        }
    }
}
