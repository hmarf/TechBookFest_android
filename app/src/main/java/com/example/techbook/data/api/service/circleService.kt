package com.example.techbook.data.api.service

import android.util.Log
import com.example.techbook.data.api.entity.CircleEntity
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CircleInterface {

    @GET("/")
    fun circleAll(): Call<List<CircleEntity>>

    @GET("/searchCircle")
    fun circleSearch(
        @Query("keyword")
        keyword: String): Call<List<CircleEntity>>
}


fun CircleService(): CircleInterface {

    val url = "http://192.168.1.3:9000"
    val okHttpClient = OkHttpClient.Builder().build()
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    return retrofit.create(CircleInterface::class.java)

}