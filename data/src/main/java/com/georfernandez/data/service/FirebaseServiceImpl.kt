package com.georfernandez.data.service

import com.georfernandez.domain.service.FirebaseService
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID
import javax.inject.Inject

class FirebaseServiceImpl @Inject constructor() : FirebaseService {
    override suspend fun savePictures(selectedPicturesForSaving: List<ByteArray>) {
        val storage = Firebase.storage
        val storageRef = storage.reference

        selectedPicturesForSaving.forEach { image ->
            val imagesRef = storageRef.child("images/selectedImage-${UUID.randomUUID()}.jpg")
            val uploadTask = imagesRef.putBytes(image)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            }
        }
    }
}
