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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThrowbacksScreen(songs: List<Song>) {
    var isLiked by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { /* Handle back button press */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            title = { },
            actions = {
                IconButton(onClick = { isLiked = !isLiked }) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.Gray
                    )
                }
                IconButton(onClick = { /* Handle download button press */ }) {
                    Icon(imageVector = Icons.Default.Download, contentDescription = "Download")
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.throwbacks),
                contentDescription = "Throwback Image",
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .background(Color(0x80000000))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Throwbacks",
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

@Preview(showBackground = true)
@Composable
fun ThrowbacksPreview() {
    ThrowbacksScreen(songs = dummySongs)
}
