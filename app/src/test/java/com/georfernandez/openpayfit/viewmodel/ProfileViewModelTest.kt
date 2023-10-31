package com.georfernandez.openpayfit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.usecases.GetMostPopularActorUseCase
import com.georfernandez.domain.utils.CoroutineResult
import com.georfernandez.openpayfit.ui.profile.ProfileViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class ProfileViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getMostPopularActorUseCase: GetMostPopularActorUseCase
    private lateinit var profileViewModel: ProfileViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)

        profileViewModel = ProfileViewModel(getMostPopularActorUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch profile info success`() {
        runTest(UnconfinedTestDispatcher()) {
            val liveDataTest = profileViewModel.getState().testObserver()
            val actor = Actor(
                id = ID,
                movieStarring = emptyList(),
                movieStarringDepartment = STARRING_DEPARTMENT,
                name = NAME,
                popularity = POPULARITY,
                biography = BIOGRAPHY,
                profilePath = PROFILE_PATH,
            )
            val actorTwo = Actor(
                id = TWO_ID,
                movieStarring = emptyList(),
                movieStarringDepartment = TWO_STARRING_DEPARTMENT,
                name = TWO_NAME,
                popularity = TWO_POPULARITY,
                biography = TWO_BIOGRAPHY,
                profilePath = TWO_PROFILE_PATH,
            )
            val actors = listOf(actor, actorTwo)

            coEvery { getMostPopularActorUseCase() } returns CoroutineResult.Success(actors)

            profileViewModel.fetchProfileInfo().join()

            assertEquals(ProfileViewModel.ProfileState.SHOW_INFO, liveDataTest.observedValues[0]?.state)
            assertEquals(actorTwo, liveDataTest.observedValues[0]?.mostPopularActor)
        }
    }

    @Test
    fun `fetch profile info error`() {
        runTest(UnconfinedTestDispatcher()) {
            val liveDataTest = profileViewModel.getState().testObserver()
            val exception = Exception()

            coEvery { getMostPopularActorUseCase() } returns CoroutineResult.Failure(exception)

            profileViewModel.fetchProfileInfo().join()

            assertEquals(ProfileViewModel.ProfileState.ON_ERROR, liveDataTest.observedValues[0]?.state)
            Assert.assertEquals(exception, liveDataTest.observedValues[0]?.exception)
        }
    }

    companion object {
        private const val ID = 9292
        private const val TWO_ID = 9293
        private const val STARRING_DEPARTMENT = "ACTOR_MOVIE_STARRING_DEPARTMENT"
        private const val TWO_STARRING_DEPARTMENT = "ACTOR_TWO_MOVIE_STARRING_DEPARTMENT"
        private const val NAME = "Keanu Reeves"
        private const val TWO_NAME = "Sandra Bullock"
        private const val POPULARITY = 52.43
        private const val TWO_POPULARITY = 543.87
        private const val BIOGRAPHY = "ACTOR_BIOGRAPHY"
        private const val TWO_BIOGRAPHY = "ACTOR_TWO_BIOGRAPHY"
        private const val PROFILE_PATH = "ACTOR_PROFILE_PATH"
        private const val TWO_PROFILE_PATH = "ACTOR_TWO_PROFILE_PATH"
    }
}
