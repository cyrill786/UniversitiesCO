package com.example.universitiesco

import retrofit2.http.GET
import com.example.universitiesco.data.University
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getUniversity(
        @Query("name") name: String
    ): List<University>
}