package com.abhishek.android_mvvm.home

import android.content.Context
import com.abhishek.android_mvvm.network.ApiServices
import com.abhishek.android_mvvm.network.RetrofitConnection
import com.abhishek.android_mvvm.network.respones.ResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteracter(private val context: Context) {


    fun postApiHitInt(listener: HomeInteracterCallback) {
        val apiService = RetrofitConnection.apiService.post
        apiService.enqueue(object : Callback<ArrayList<ResponsePost>> {
            override fun onFailure(call: Call<ArrayList<ResponsePost>>, t: Throwable) {
                listener.apiError()
            }

            override fun onResponse(call: Call<ArrayList<ResponsePost>>, response: Response<ArrayList<ResponsePost>>) {
                listener.apiSuccess(response)

            }
        })
    }

    interface HomeInteracterCallback {
        fun apiSuccess(response: Response<ArrayList<ResponsePost>>)
        fun apiError()
    }
}