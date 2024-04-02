package com.example.spotistics

data class Song(
    val musicId: Int,
    val coverResourceId: Int,
    val title: String,
    val artist: String
)

val dummySongs = listOf(
    Song(1, R.drawable.sufjan, "Will Anybody Ever Love Me?", "Sufjan Stevens"),
    Song(2, R.drawable.as_it_was, "As it was", "Harry Styles"),
    Song(3, R.drawable.get_him_back, "Get him back!", "Olivia Rodrigo"),
    Song(4, R.drawable.lana, "A&W", "Lana Del Rey"),
    Song(5, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(6, R.drawable.kill_bill, "Kill Bill", "SZA"),
    Song(7, R.drawable.supershy, "Super Shy", "NewJeans"),
    Song(8, R.drawable.wednesday, "Chosen to Deserve", "Wednesday"),
    Song(9, R.drawable.vampire, "Vampire", "Olivia Rodrigo"),
    Song(10, R.drawable.taylor_swift, "Lover", "Taylor Swift"),
)

val dummySongs2 = listOf(
    Song(1, R.drawable.taylor_swift, "Lover", "Taylor Swift"),
    Song(2, R.drawable.vampire, "Vampire", "Olivia Rodrigo"),
    Song(3, R.drawable.get_him_back, "Get him back!", "Olivia Rodrigo"),
    Song(4, R.drawable.supershy, "Super Shy", "NewJeans"),
    Song(5, R.drawable.wednesday, "Chosen to Deserve", "Wednesday"),
    Song(6, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(7, R.drawable.sufjan, "Will Anybody Ever Love Me?", "Sufjan Stevens"),
    Song(8, R.drawable.kill_bill, "Kill Bill", "SZA"),
    Song(9, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(10, R.drawable.lana, "A&W", "Lana Del Rey"),
    )