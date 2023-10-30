package com.georfernandez.data.database

import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.data.mapper.mapToDataBaseActor
import com.georfernandez.data.mapper.mapToDataBaseKnownFor
import com.georfernandez.data.mapper.mapToLocalActorList
import com.georfernandez.domain.database.ActorRepository
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.utils.CoroutineResult

class ActorRepositoryImpl(
    private val actorDao: ActorDao,
    private val movieStarringDao: MovieStarringDao,
) : ActorRepository {
    override suspend fun insertActorToDB(popularPersons: List<Actor>) {
        popularPersons.forEach { actor ->
            actorDao.insertActor(actor.mapToDataBaseActor())

            actor.movieStarring.forEach {
                movieStarringDao.insertMovieStarring(it.mapToDataBaseKnownFor(actor.id))
            }
        }
    }

    override suspend fun getDBActors(): CoroutineResult<List<Actor>> =
        actorDao.getDBActor().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToLocalActorList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }
}
