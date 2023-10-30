package com.georfernandez.domain.database

import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.utils.CoroutineResult

interface ActorRepository {
    suspend fun insertActorToDB(popularPersons: List<Actor>)
    suspend fun getDBActors(): CoroutineResult<List<Actor>>
}
