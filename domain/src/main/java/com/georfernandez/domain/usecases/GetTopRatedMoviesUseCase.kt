package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetTopRatedMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val tMDBService: TMDBService,
    private val tMDBRepository: TMDBRepository,
) : GetTopRatedMoviesUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = tMDBService.getTopRatedMovies()) {
            is CoroutineResult.Success -> {
                tMDBRepository.insertTopRatedMoviesToDB(serviceResult.data)
                tMDBRepository.getDBTopRatedMovies()
            }

            is CoroutineResult.Failure -> {
                tMDBRepository.getDBTopRatedMovies()
            }
        }
    }
}
