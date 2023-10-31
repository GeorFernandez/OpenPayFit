package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetMostPopularMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetMostPopularMoviesUseCaseImpl @Inject constructor(
    private val tMDBService: TMDBService,
    private val tMDBRepository: TMDBRepository,
) : GetMostPopularMoviesUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = tMDBService.getPopularMovies()) {
            is CoroutineResult.Success -> {
                tMDBRepository.insertPopularMovieToDB(serviceResult.data)
                tMDBRepository.getDBPopularMovie()
            }

            is CoroutineResult.Failure -> {
                tMDBRepository.getDBPopularMovie()
            }
        }
    }
}
