package com.example.spotistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotistics.databinding.ActivityWrappedBinding

class WrappedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrapped)

        // Generate temporary list of pop songs
        val popSongs = mutableListOf<String>()
        popSongs.add("Song 1")
        popSongs.add("Song 2")
        popSongs.add("Song 3")
        popSongs.add("Song 4")
        popSongs.add("Song 5")

        // Add more songs as needed

        // Generate temporary list of pop artists
        val popArtists = mutableListOf<String>()
        popArtists.add("Artist 1")
        popArtists.add("Artist 2")
        popArtists.add("Artist 3")
        popArtists.add("Artist 4")
        popArtists.add("Artist 5")

        // Add more artists as needed

        // Inflate the layout and get the binding object
        val binding = ActivityWrappedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView for pop songs
        val songAdapter = SimpleAdapter(popSongs)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = songAdapter

        // Set up RecyclerView for pop artists
        val artistAdapter = SimpleAdapter(popArtists)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.adapter = artistAdapter


    }
}