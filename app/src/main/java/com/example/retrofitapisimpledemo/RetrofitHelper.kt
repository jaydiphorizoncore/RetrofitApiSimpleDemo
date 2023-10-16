package com.example.retrofitapisimpledemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl = "https://quotable.io/"
    private const val Url = "https://reqres.in/api/"

    //get the Retrofit object

    // on below line we are creating a retrofit
    // builder and passing our base url

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance2(): Retrofit {
        return Retrofit.Builder().baseUrl(Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}