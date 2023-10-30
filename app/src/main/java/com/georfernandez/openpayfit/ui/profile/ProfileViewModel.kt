package com.georfernandez.openpayfit.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.usecases.GetMostPopularActorUseCase
import com.georfernandez.domain.utils.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val mostPopularActor: GetMostPopularActorUseCase) : ViewModel() {
    private var mutableState = MutableLiveData<ProfileData>()
    fun getState(): LiveData<ProfileData> = mutableState
    private var mutableLoading = MutableLiveData<ProfileData>()
    val loading: LiveData<ProfileData> = mutableLoading
    fun fetchProfileInfo() = viewModelScope.launch {
        mutableLoading.value = ProfileData(state = ProfileState.LOADING)

        withContext(Dispatchers.IO) { mostPopularActor() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        mutableState.value =
                            ProfileData(state = ProfileState.SHOW_INFO, mostPopularActor = result.data.maxBy { it.popularity })
                    } else {
                        mutableState.value = ProfileData(state = ProfileState.EMPTY_STATE)
                    }
                }

                is CoroutineResult.Failure -> {
                    mutableState.value = ProfileData(state = ProfileState.ON_ERROR, exception = result.exception)
                }
            }
        }
    }

    data class ProfileData(
        val state: ProfileState,
        val mostPopularActor: Actor? = null,
        val exception: Exception? = null,
    )

    enum class ProfileState {
        SHOW_INFO,
        ON_ERROR,
        EMPTY_STATE,
        LOADING,
    }
}
