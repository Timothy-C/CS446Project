package com.example.spotistics

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.spotistics.ui.theme.SpotisticsTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.IconButton


@OptIn(ExperimentalMaterial3Api::class) // Opt-in for all Material 3 experimental APIs
@Composable
fun RecommendationScreen(songs: List<Song>) {
    var isLiked by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { /* Handle back button press */ }) {
                    androidx.compose.material.Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            title = {  },
            actions = {
                // Toggle the isLiked state on click
                IconButton(onClick = { isLiked = !isLiked }) {
                    androidx.compose.material.Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.Gray // Optional: Change the color when liked
                    )
                }
                IconButton(onClick = { /* Handle download button press */ }) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download"
                    )
                }
            }
        )

        // This would be a placeholder for your recommendation image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.recommended),
                contentDescription = "Recommendation Image",
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .background(Color(0x80000000))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Recommendations",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "30 songs to sing in the shower",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(songs) { song ->
                SongItem(song)
                Divider()
            }
        }
    }
}

// Preview for RecommendationScreen
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpotisticsTheme {
        RecommendationScreen(songs = dummySongs)
    }
}


@Composable
fun SongItem(song: Song) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = song.coverResourceId),
            contentDescription = "Cover Image",
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f) // This ensures the column takes up most of the row space, pushing the IconButton to the end
        ) {
            Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = song.artist, style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = { /* Implement action for more options here */ }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More Options")
        }
    }
}