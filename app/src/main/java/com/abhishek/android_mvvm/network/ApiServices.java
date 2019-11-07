package com.abhishek.android_mvvm.network;

import com.abhishek.android_mvvm.network.respones.ResponsePost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("/posts")
    Call<ArrayList<ResponsePost>>getPost();
}
