package com.example.techbook.data.api.service

import com.example.techbook.data.api.entity.CircleEntityResult
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CircleInterface {

    @GET("/")
    fun circleAll(): Call<CircleEntityResult>

    @GET("/searchCircle")
    fun circleSearch(
        @Query("keyword")
        keyword: String): Call<CircleEntityResult>
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