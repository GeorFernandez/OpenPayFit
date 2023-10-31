package com.georfernandez.domain.usecases

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GetMostPopularActorUseCaseTest {
    @MockK
    private lateinit var tMDBService: TMDBService

    @MockK
    private lateinit var tMDBRepository: TMDBRepository
    private lateinit var getMostPopularActorUseCase: GetMostPopularActorUseCase

    @Before
    fun init() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)

        getMostPopularActorUseCase = GetMostPopularActorUseCaseImpl(tMDBService, tMDBRepository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `use case succeed`() {
        runTest(UnconfinedTestDispatcher()) {
            val actors = listOf<Actor>()

            coEvery { tMDBService.getMostPopularActors() } returns CoroutineResult.Success(actors)
            coEvery { tMDBRepository.getDBActors() } returns CoroutineResult.Success(actors)

            val result = getMostPopularActorUseCase()

            coVerify { tMDBService.getMostPopularActors() }
            coVerify { tMDBRepository.insertActorToDB(actors) }
            coVerify { tMDBRepository.getDBActors() }

            assertEquals(actors, (result as CoroutineResult.Success).data)
        }
    }

    @Test
    fun `use case fails`() {
        runTest(UnconfinedTestDispatcher()) {
            val actors = listOf<Actor>()
            val exception = Exception()

            coEvery { tMDBService.getMostPopularActors() } returns CoroutineResult.Failure(exception)
            coEvery { tMDBRepository.getDBActors() } returns CoroutineResult.Success(actors)

            val result = getMostPopularActorUseCase()

            coVerify { tMDBService.getMostPopularActors() }
            coVerify { tMDBRepository.getDBActors() }

            assertEquals(actors, (result as CoroutineResult.Success).data)
        }
    }
}
