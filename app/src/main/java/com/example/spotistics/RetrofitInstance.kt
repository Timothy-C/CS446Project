package com.example.spotistics

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

var access_token = ""

interface ApiService {
    @GET("auth/refreshToken")
    fun refreshToken(): Call<JsonObject>
}

object RetrofitInstance {
    private const val BASE_URL = "http://3.144.186.12/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Cookie", "refresh_token=insert refresh token here")
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

private fun setAccessToken(newToken: String) {
    access_token = newToken
}

fun getAccessToken() {
    val call = RetrofitInstance.api.refreshToken()
    call.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                val jsonObject = JSONObject(Gson().toJson(response.body()))
                if (response.body() != null) {
                    Log.i("tokentest", "access_token: ${jsonObject.get("access_token")}")
                    val accessToken = jsonObject.get("access_token").toString()
                    setAccessToken(accessToken)
                }
                Log.i("tokentest", "Response successful: ${response.message()}")
            } else {
                Log.i("tokentest", "Response unsuccessful: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.i("tokentest", "Request failed: $t")
        }
    })
}