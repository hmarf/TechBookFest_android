package com.example.techbook.data.api.service

import com.example.techbook.data.api.entity.CircleEntityResult
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CircleInterface {

    @GET("/")
    fun circleAll(): Deferred<CircleEntityResult>

    @GET("/searchCircle")
    fun circleSearch(
        @Query("keyword")
        keyword: String
    ): Deferred<CircleEntityResult>
}

fun circleService(): CircleInterface {

    val url = "http://localhost:8111"
    val okHttpClient = OkHttpClient.Builder().build()
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    return retrofit.create(CircleInterface::class.java)

}