package com.example.spotistics

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("spotify/history")
    suspend fun getSongStats(@Query("limit") limit: Int): List < SongStat >
}

object RetrofitInstance {
    private const val BASE_URL = "https://3.144.186.12:3000"
    //private const val BASE_URL = "https://jsonplaceholder.typicode.com/todos/1"
    // Todo: Store access token here

    // Manually add your cookie to the OkHttpClient before building the Retrofit instance
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                // Add your custom cookie here
                .addHeader("Cookie", "access_token=access_token")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}