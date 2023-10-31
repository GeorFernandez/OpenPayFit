package com.georfernandez.domain.service

interface FirebaseService {
    suspend fun savePictures(selectedPicturesForSaving: List<ByteArray>)
}
