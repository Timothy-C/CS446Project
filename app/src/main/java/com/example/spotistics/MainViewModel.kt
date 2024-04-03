package com.example.spotistics

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api
    val songs: MutableState<List<SongStat>> = mutableStateOf(emptyList())
    /*
    fun getHistory() {
        viewModelScope.launch {
            try {
                val response = apiService.getSongStats(limit=20)
                Log.d("TAG", response.toString())
                if (response.isNotEmpty()) {
                    songs.value = response
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e("TAG", e.toString())
            }
        }
    }*/
}