package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.ActorRepository
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.service.ActorService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetMostPopularActorUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Actor>>
}

class GetMostPopularActorUseCaseImpl @Inject constructor(
    private val ActorService: ActorService,
    private val ActorRepository: ActorRepository,
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
