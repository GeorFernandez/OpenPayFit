package com.georfernandez.domain.service

import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.utils.CoroutineResult

interface ActorService {
    suspend fun getMostPopularActors(): CoroutineResult<List<Actor>>
}
