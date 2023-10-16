package com.example.retrofitapisimpledemo

import com.example.retrofitapisimpledemo.model.DataModel
import com.example.retrofitapisimpledemo.model.QuoteListData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuotesApi {

    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteListData>

    @POST("users")
    fun createPost(@Body dataModal: DataModel): retrofit2.Call<DataModel>

}