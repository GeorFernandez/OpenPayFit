package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetMostPopularActorUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Actor>>
}

class GetMostPopularActorUseCaseImpl @Inject constructor(
    private val ActorService: TMDBService,
    private val ActorRepository: TMDBRepository,
) : GetMostPopularActorUseCase {
    override suspend fun invoke(): CoroutineResult<List<Actor>> {
        return when (val serviceResult = ActorService.getMostPopularActors()) {
            is CoroutineResult.Success -> {
                ActorRepository.insertActorToDB(serviceResult.data)
                ActorRepository.getDBActors()
            }

            is CoroutineResult.Failure -> {
                ActorRepository.getDBActors()
            }
        }
    }
}
