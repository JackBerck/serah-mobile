package com.example.serah.data.network


import com.example.serah.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): Response<SearchResponse>
}