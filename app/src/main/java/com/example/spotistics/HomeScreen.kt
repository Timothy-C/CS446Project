package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.quicksandFamily

@Composable
fun Home(innerPadding: PaddingValues, viewModel: AppViewModel) {
    val rowScrollState = rememberScrollState()
    val recommendations by viewModel.recommendations.collectAsState()
    val popularAlbums by viewModel.popularAlbums.collectAsState()
    val uriHandler = LocalUriHandler.current

    // Form API requests to retrieve recommendations and popular albums
    LaunchedEffect(Unit) {
        val body = mapOf(
            "limit" to 10,
            "offset" to 0,
            "artists" to mapOf(
                "useTop" to true,
                "range" to "long_term"
            )
        )
        viewModel.getRecommendations(body)
        viewModel.getPopularAlbums()
    }

    LazyColumn(
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        // Display featured song at top of screen
        val featured = recommendations.recommendations[0]
        item {
            Text(
                text = "Featured",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(18.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = featured.typeDetails.album.images[0].url,
                    contentDescription = "Album cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(170.dp)
                        .fillMaxWidth(),
                )
                Text(
                    modifier = Modifier.padding(15.dp, 15.dp, 15.dp, 4.dp),
                    text = featured.typeDetails.album.name,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 15.dp),
                    text = featured.typeDetails.artists[0].name,
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(15.dp, 0.dp, 0.dp, 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.spotify_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                uriHandler.openUri(featured.typeDetails.album.externalUrls.spotify)
                            }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri(featured.typeDetails.album.externalUrls.spotify)
                            },
                        text = "Listen on Spotify",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
//            Spacer(modifier = Modifier.height(5.dp))
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//            ) {
//
//            }
            Spacer(modifier = Modifier.height(30.dp))
        }

        // Display popular albums with horizontal scroll
        item {
            Text(
                text = "Popular",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
                    .padding(innerPadding)
            ) {
                for (item in popularAlbums.recommendations) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .size(width = 170.dp, height = 170.dp)
                    ) {
                        AsyncImage(
                            model = item.typeDetails.album.images[0].url,
                            contentDescription = "Album cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

        // Display preview of top recommendations
        item {
            Text(
                text = "Top Recommendations",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                for (i in 1..< recommendations.recommendations.size) {
                    val recSong = recommendations.recommendations[i]
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.9f),
                        ),
                        modifier = Modifier
                            .height(170.dp)
                            .fillMaxWidth()
                    ) {
                        Row() {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(170.dp)
                            ) {
                                AsyncImage(
                                    model = recSong.typeDetails.album.images[0].url,
                                    contentDescription = "Album cover",
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp),
                            ) {
                                Text(
                                    text = recSong.typeDetails.track.name,
                                    color = Color.Black,
                                    fontSize = 22.sp,
                                    fontFamily = quicksandFamily,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = recSong.typeDetails.artists[0].name,
                                    color = Color.Gray,
                                    fontSize = 17.sp,
                                    fontFamily = quicksandFamily,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(
                                    modifier = Modifier
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                        .padding(0.dp, 0.dp, 0.dp, 15.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.spotify_logo),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clickable {
                                                uriHandler.openUri(recSong.typeDetails.track.externalUrls.spotify)
                                            }
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                uriHandler.openUri(recSong.typeDetails.track.externalUrls.spotify)
                                            },
                                        text = "Listen on Spotify",
                                        color = Color.Gray,
                                        fontSize = 15.sp,
                                        fontFamily = quicksandFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}
