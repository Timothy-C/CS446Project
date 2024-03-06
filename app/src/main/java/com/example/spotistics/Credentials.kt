package com.example.spotistics

import com.spotify.android.appremote.api.SpotifyAppRemote

class Credentials {
    val clientId = "dcb7c8ef25dd48c2b832fd73164d9f4c"
    val redirectUri = "http://localhost:3000/auth/callback"
    var spotifyAppRemote: SpotifyAppRemote? = null
}