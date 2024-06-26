package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.quicksandFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Throwbacks(innerPadding: PaddingValues, colScrollState: LazyListState, songs: List<Song>) {
    Column(
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
//        TopAppBar(
//            navigationIcon = {
//                IconButton(onClick = { /* Handle back button press */ }) {
//                    androidx.compose.material.Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//                }
//            },
//            title = {  },
//            actions = {
//                // Toggle the isLiked state on click
//                IconButton(onClick = { isLiked = !isLiked }) {
//                    androidx.compose.material.Icon(
//                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
//                        contentDescription = "Like",
//                        tint = if (isLiked) Color.Red else Color.Gray // Optional: Change the color when liked
//                    )
//                }
//                IconButton(onClick = { /* Handle download button press */ }) {
//                    androidx.compose.material.Icon(
//                        imageVector = Icons.Default.Download,
//                        contentDescription = "Download"
//                    )
//                }
//            }
//        )
        Text(
            text = "Throwbacks",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(18.dp))
        // This would be a placeholder for your recommendation image
        Card(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.throwbacks),
                    contentDescription = "Recommendation Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Column(
                    modifier = Modifier
                        .background(Color(0xB3F8F4F4))
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Songs of decades past",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(songs) { song ->
                SongItem(song, Color.White)
                Divider()
            }
        }
    }
}


// Preview for RecommendationScreen
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SpotisticsTheme {
//        RecommendationScreen(songs = dummySongs)
//    }
//}
