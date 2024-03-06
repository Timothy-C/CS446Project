package com.example.spotistics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spotistics.ui.theme.SpotisticsTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.spotify.android.appremote.api.SpotifyAppRemote


class MainActivity : AppCompatActivity() {
    private val clientId = "dcb7c8ef25dd48c2b832fd73164d9f4c"
    private val redirectUri = "http://localhost:3000/auth/callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mainactivity", "mainactivity")
        setContent {
            SpotisticsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    RecommendationScreen(songs = dummySongs)
                    ThrowbacksScreen(songs = dummySongs2)
//                    Login()

//                    Settings()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpotisticsTheme {
        Greeting("Android")
    }
}