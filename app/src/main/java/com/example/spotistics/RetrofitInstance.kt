package com.example.spotistics

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

//class InitialData (
//    @field:SerializedName("accessToken") var accessToken: String,
//    @field:SerializedName("spotifyUser") var spotifyUser: String,
//)

var accessToken: MutableState<String> = mutableStateOf("")
var spotifyUser: MutableState<String> = mutableStateOf("")

interface ApiService {
    //@GET("spotify/history")
    //suspend fun getSongStats(@Query("limit") limit: Int): List < SongStat >

    @GET("auth/refreshToken")
    fun refreshToken(): Call<JsonObject>

    @GET("spotify/user")
    fun spotifyUser(): Call<JsonObject>
}

object RetrofitAccessToken {
    private const val BASE_URL = "http://3.144.186.12/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Cookie", "refresh_token=insert refresh token")
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

object RetrofitInstance {
    private const val BASE_URL = "http://3.144.186.12/"
    //private const val BASE_URL = "https://jsonplaceholder.typicode.com/todos/1"
    // Todo: Store access token here

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
    accessToken.value = newToken
}

private fun setSpotifyUser(user: String) {
    spotifyUser.value = user
}

fun getUser(): String {
    return spotifyUser.value
}

fun getAccessToken() {
    val call = RetrofitAccessToken.api.refreshToken()
    call.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                val jsonObject = JSONObject(Gson().toJson(response.body()))
                if (response.body() != null) {
                    Log.i("API", "access_token: ${jsonObject.get("access_token")}")
                    val accessToken = jsonObject.get("access_token").toString()
                    setAccessToken(accessToken)
                }
                Log.i("API", "Response successful: ${response.message()}")
            } else {
                Log.i("API", "Response unsuccessful: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.i("API", "Request failed: $t")
        }
    })
}

fun getSpotifyUser() {
    val call = RetrofitInstance.api.spotifyUser()
    call.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                val jsonObject = JSONObject(Gson().toJson(response.body()))
                if (response.body() != null) {
                    val displayName = jsonObject.get("display_name").toString()
                    Log.i("API", "spotify user: $displayName")
                    setSpotifyUser(displayName)
                }
                Log.i("API", "Response successful: ${response.message()}")
            } else {
                Log.i("API", "Response unsuccessful: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.i("API", "Request failed: $t")
        }
    })
}