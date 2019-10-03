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

interface CircleService {

    companion object {

    }

    @GET("/")
    fun circleAll(
    ): Call<List<CircleEntity>>

    @GET("/searchCircle")
    fun circleSearch(
        @Query("keyword") keyword: String
    ): Call<List<CircleEntity>>
}


class CircleRepository() {

    private var circleService: CircleService

    init {
        val url = "http://192.168.1.3:9000"
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
        circleService = retrofit.create(CircleService::class.java)
    }

    // エラー処理は省いています
    fun getAllCircle(callback: (List<CircleEntity>) -> Unit) {
        Log.v("ccccc","aaa")
        circleService.circleAll().enqueue(object : Callback<List<CircleEntity>> {

            override fun onResponse(call: Call<List<CircleEntity>>?, response: Response<List<CircleEntity>>?) {
                Log.v("ok","ok")
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.v("ok",it.toString())
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CircleEntity>>?, t: Throwable?) {
                Log.v("nnn","nnn")
            }
        })
    }

    fun getSearchCircle(keyword: String, callback: (List<CircleEntity>) -> Unit) {
        circleService.circleSearch(keyword = keyword).enqueue(object : Callback<List<CircleEntity>> {

            override fun onResponse(call: Call<List<CircleEntity>>?, response: Response<List<CircleEntity>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CircleEntity>>?, t: Throwable?) {}
        })
    }

}