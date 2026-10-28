package com.example.universitiesco.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitiesco.ApiClient
import com.example.universitiesco.data.University
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UniversityViewModel : ViewModel() {
    val university = MutableStateFlow<List<University>>(emptyList())

    fun loadUniversity(name: String) {
        viewModelScope.launch {
            try {
                university.value = ApiClient.apiService.getUniversity(name)
            } catch (e: Exception) {
                Log.e("PostVM", "Erreur: ${e.message}")
            }
        }
    }
}