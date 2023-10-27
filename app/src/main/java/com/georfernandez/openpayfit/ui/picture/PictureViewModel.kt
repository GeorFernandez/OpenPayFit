package com.georfernandez.openpayfit.ui.picture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PictureViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Picture Fragment"
    }
    val text: LiveData<String> = _text
}
