package com.mbougar.adatapptareasandroid.data.remote

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.mbougar.adatapptareasandroid.data.repository.UserPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://adatprojectoapitareashogar.onrender.com"

    val gson = GsonBuilder()
        .setLenient()  // Permite manejar JSON más flexibles
        .create()

    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}