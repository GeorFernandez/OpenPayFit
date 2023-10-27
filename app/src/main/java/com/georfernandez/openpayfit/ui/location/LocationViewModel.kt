package com.georfernandez.openpayfit.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georfernandez.openpayfit.R

class LocationViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Location Fragment"
    }
    val text: LiveData<String> = _text
}
