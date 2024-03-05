package com.example.spotistics

data class Song(
    val musicId: Int,
    val coverResourceId: Int,
    val title: String,
    val artist: String
)

val dummySongs = listOf(
    Song(1, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(2, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(3, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(4, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(5, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(6, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(7, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(8, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(9, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen")
)