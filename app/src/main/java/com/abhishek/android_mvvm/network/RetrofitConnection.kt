package com.abhishek.android_mvvm.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConnection {
    val BASE_URL_WEATHER="https://jsonplaceholder.typicode.com"
 //posts
    private val retrofitInstance: Retrofit
        get() {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder().baseUrl(BASE_URL_WEATHER).addConverterFactory(
                GsonConverterFactory.create(gson)).build() }


    val apiService: ApiServices
        get() = retrofitInstance.create(ApiServices::class.java)
}