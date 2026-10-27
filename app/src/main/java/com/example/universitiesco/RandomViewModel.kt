package com.example.universitiesco

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RandomViewModel : ViewModel() {
    var name by mutableStateOf("")
    var country by mutableStateOf("")
    var code by mutableStateOf("")
    var state by mutableStateOf("")
    var page by mutableStateOf("")
}
