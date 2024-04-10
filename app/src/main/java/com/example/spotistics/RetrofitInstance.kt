package com.example.spotistics

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Set up API requests to retrieve display data
interface ApiService {
    @GET("auth/refreshToken")
    suspend fun getAccessToken(): AccessToken

    @GET("/spotify/user")
    suspend fun getUser(): User

    @POST("/spotify/search")
    @JvmSuppressWildcards
    suspend fun getSearchResult(@Body body: Map<String, Any>): SearchResult

    @POST("/spotify/recommend")
    @JvmSuppressWildcards
    suspend fun getRecommendations(@Body body: Map<String, Any>): RecommendationResult

    @GET("/spotistics/stats")
    suspend fun getStats(@Query("dateStart") dateStart: Long, @Query("dateEnd") dateEnd: Long): Stats

    @GET("/spotistics/history")
    suspend fun getHistory(
        @Query("dateStart") dateStart: Long,
        @Query("dateEnd") dateEnd: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): History
}

// Create HTTP client using Retrofit
object RetrofitInstance {
    private const val BASE_URL = "http://3.144.186.12/"
    var accessToken = ""

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    private class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Cookie", "refresh_token=$refreshToken; access_token=$accessToken")
                .build()
            return chain.proceed(request)
        }
    }

    suspend fun refreshAccessToken() {
        try {
            val accessTokenResponse = api.getAccessToken()
            accessToken = accessTokenResponse.accessToken
        } catch (e: Exception) {
            Log.e("API", "Failed to refresh access token: ${e.localizedMessage}")
            throw e
        }
    }
}
