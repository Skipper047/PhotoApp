package com.example.photoapp

import android.app.Application
import com.example.photoapp.network.PixabayApi
import com.example.photoapp.network.RetrofitService

class App : Application() {

    companion object{
        lateinit var api: PixabayApi
    }
    override fun onCreate() {
        super.onCreate()
        val retrofitService= RetrofitService()
        api = retrofitService.getApi()
    }
}