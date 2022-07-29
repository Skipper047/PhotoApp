package com.example.photoapp.network

import com.example.photoapp.model.PixabayModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
//https://pixabay.com/api/?key=28903521-1ce3f723e180c42501727012e&q=yellow+flowers&image_type=photo
    @GET("api/")
    fun getImageByWord(@Query("key")key:String="28903521-1ce3f723e180c42501727012e",
                       @Query("q")keyWord:String,
                       @Query("page") page: Int,
                       @Query("per_page") perPage: Int = 10

                       ): retrofit2.Call<PixabayModel>
}