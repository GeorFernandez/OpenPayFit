package com.georfernandez.openpayfit.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georfernandez.openpayfit.R
import kotlin.coroutines.coroutineContext

class MoviesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Movies Fragment"
    }
    val text: LiveData<String> = _text
}
