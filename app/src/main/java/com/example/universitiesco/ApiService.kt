package com.example.universitiesco

import retrofit2.http.GET
import com.example.universitiesco.data.University

interface ApiService {
    @GET("university")
    suspend fun getUniversity(): List<University>
}