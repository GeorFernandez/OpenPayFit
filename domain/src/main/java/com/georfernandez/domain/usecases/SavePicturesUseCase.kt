package com.georfernandez.domain.usecases

import com.georfernandez.domain.service.FirebaseService
import javax.inject.Inject

interface SavePicturesUseCase {
    suspend operator fun invoke(selectedPictures: List<ByteArray>)
}

class SavePicturesUseCaseImpl @Inject constructor(private val firebaseService: FirebaseService) : SavePicturesUseCase {
    override suspend operator fun invoke(selectedPictures: List<ByteArray>) {
        firebaseService.savePictures(selectedPictures)
    }
}
