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

            override fun onResponse(
                call: Call<ArrayList<ResponsePost>>,
                response: Response<ArrayList<ResponsePost>>
            ) {


                if (response.isSuccessful) {


                    listener.apiSuccess(response.body()!!)
                }
            }
        })
    }

    fun addItem(listener: HomeInteracterCallback)
    {
        var addItem:ResponsePost= ResponsePost()
        addItem!!.body="Helllo"
        addItem!!.tittle="bhfbf"
        addItem.id="jgf"
        addItem.userId="hbhjfh"
        listener.addItem(addItem)
    }

    interface HomeInteracterCallback {
        fun apiSuccess(postList: ArrayList<ResponsePost>)
        fun apiError()
        fun addItem(addItem:ResponsePost)
        // fun apiSuccess(homeAdapter: HomeAdapter)
    }
}