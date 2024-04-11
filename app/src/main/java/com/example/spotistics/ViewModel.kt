package com.example.spotistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AppViewModel() : ViewModel() {
    private val retrofitInstance = RetrofitInstance
    val userName: MutableStateFlow<User> = MutableStateFlow(User(""))
    val artist: MutableStateFlow<SearchResult> = MutableStateFlow(emptySearchResult)
    val recommendations: MutableStateFlow<RecommendationResult> = MutableStateFlow(emptyRecommendation)
    val popularAlbums: MutableStateFlow<RecommendationResult> = MutableStateFlow(emptyRecommendation)
    val statistics: MutableStateFlow<Stats> = MutableStateFlow(Stats(0, 0))
    val history: MutableStateFlow<History> = MutableStateFlow(History(listOf(TrackHistory("", ""))))
    val navigateToHome: MutableStateFlow<Boolean> = MutableStateFlow(false)

    // Store access token after login and trigger navigation to home screen
    fun setAccessToken(accessToken: String) {
        retrofitInstance.accessToken = accessToken
        navigateToHome.value = true
    }

    // Retrieve Spotify user profile data
    suspend fun getUser() {
        viewModelScope.launch {
            try {
                userName.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getUser()
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        userName.value = withContext(Dispatchers.IO) {
                            retrofitInstance.api.getUser()
                        }
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }

    // Retrieve Spotify ID for artist entered by the user
    suspend fun getArtist(artistName: String) {
        val body = mapOf(
            "limit" to 1,
            "offset" to 0,
            "type" to listOf("artist"),
            "artist" to artistName
        )

        viewModelScope.launch {
            try {
                artist.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getSearchResult(body)
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        artist.value = retrofitInstance.api.getSearchResult(body)
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }

    // Retrieve popular recommended albums
    suspend fun getPopularAlbums() {
        val body = mapOf(
            "limit" to 5,
            "offset" to 0,
            "artists" to mapOf("useTop" to true),
            "popularity" to mapOf(
                "include" to true, "min" to 90, "max" to 100, "target" to 100
            )
        )

        viewModelScope.launch {
            try {
                popularAlbums.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getRecommendations(body)
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        popularAlbums.value = retrofitInstance.api.getRecommendations(body)
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }

    // Retrieve recommendations from Spotify
    suspend fun getRecommendations(body: Map<String, Any>) {
        viewModelScope.launch {
            try {
                recommendations.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getRecommendations(body)
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        recommendations.value = retrofitInstance.api.getRecommendations(body)
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }

    // Retrieve user statistics from Spotistics
    suspend fun getStatistics(dateStart: Long, dateEnd: Long) {
        viewModelScope.launch {
            try {
                statistics.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getStats(dateStart, dateEnd)
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        statistics.value = retrofitInstance.api.getStats(dateStart, dateEnd)
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }

    // Retrieve user listening history from Spotistics
    suspend fun getHistory(dateStart: Long, dateEnd: Long) {
        viewModelScope.launch {
            try {
                history.value = withContext(Dispatchers.IO) {
                    retrofitInstance.api.getHistory(dateStart, dateEnd, 20, 0)
                }
            } catch (e: HttpException) {
                if (e.code() == 502) {
                    retrofitInstance.refreshAccessToken()
                    try {
                        history.value = retrofitInstance.api.getHistory(dateStart, dateEnd, 20, 0)
                    } catch (e: HttpException) {
                        throw e
                    }
                } else {
                    throw e
                }
            }
        }
    }
}