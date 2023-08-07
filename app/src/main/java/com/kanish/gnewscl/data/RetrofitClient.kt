package com.kanish.gnewscl.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL="https://newsdata.io"

    private fun getRetroFit():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build()
    }
    val apiService= getRetroFit().create(ApiService::class.java)
}