package com.georfernandez.data.service

import com.georfernandez.data.mapper.mapToLocalActorList
import com.georfernandez.data.service.api.TMDBApi
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.service.ActorService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

class ActorServiceImpl @Inject constructor(private val TMDBApi: TMDBApi) : ActorService {
    override suspend fun getMostPopularActors(): CoroutineResult<List<Actor>> {
        try {
            val callResponse = TMDBApi.getPopularActors()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToLocalActorList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
        return CoroutineResult.Failure(Exception())
    }
}
