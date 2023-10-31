package com.georfernandez.openpayfit.ui.picture

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georfernandez.domain.usecases.SavePicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor(
    private val savePicturesUseCase: SavePicturesUseCase,
) : ViewModel() {
    private var mutableState = MutableLiveData<PictureData>()
    fun getState(): LiveData<PictureData> = mutableState
    private val selectedImages: ArrayList<Bitmap> = ArrayList()
    fun onGalleryPictureSelection() {
        mutableState.value = PictureData(state = PictureState.GALLERY)
    }

    fun onSaveSelection(networkConnected: Boolean) = viewModelScope.launch {
        if (networkConnected) {
            withContext(Dispatchers.IO) { async { savePicturesUseCase(transformPictures()) } }.await()

            mutableState.value = PictureData(state = PictureState.SAVED_PICTURES)
        } else {
            mutableState.value = PictureData(state = PictureState.ON_ERROR)
        }
    }

    private fun transformPictures(): List<ByteArray> {
        val byteArrayList = mutableListOf<ByteArray>()

        selectedImages.forEach { bitmap ->
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            byteArrayList.add(baos.toByteArray())
        }

        return byteArrayList
    }

    fun savePictures(selectedPicturesForSave: MutableList<Bitmap>) {
        this.selectedImages.addAll(selectedPicturesForSave)
    }

    data class PictureData(
        val state: PictureState,
        val pictures: List<Bitmap> = emptyList(),
    )

    enum class PictureState {
        GALLERY,
        SAVED_PICTURES,
        ON_ERROR,
    }
}
