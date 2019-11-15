package com.example.adapt.model.api

import androidx.databinding.Observable
import com.example.adapt.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("top-headlines")
    fun getRepo(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<News>
}