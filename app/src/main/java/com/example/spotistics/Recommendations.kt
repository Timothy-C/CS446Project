package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.material3.MaterialTheme

data class MusicItem(
    val id: Int,
    val coverImageUrl: String,
    val songName: String,
    val singerName: String
)

@Composable
fun MusicItemComposable(musicItem: MusicItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(data = musicItem.coverImageUrl),
            contentDescription = "Music cover for ${musicItem.songName}",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = musicItem.songName)
            Text(text = musicItem.singerName)
        }
    }
}
@Composable
fun MusicListComposable(musicList: List<MusicItem>) {
    LazyColumn {
        items(musicList) { musicItem ->
            MusicItemComposable(musicItem = musicItem)
        }
    }
}
