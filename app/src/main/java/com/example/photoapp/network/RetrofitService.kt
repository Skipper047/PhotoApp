package com.example.photoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
//https://pixabay.com/api/?key=28903521-1ce3f723e180c42501727012e&q=yellow+flowers&image_type=photo

    private val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApi(): PixabayApi = retrofit.create(PixabayApi::class.java)
}