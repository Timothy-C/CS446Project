package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.quicksandFamily

@Composable
fun SearchResults(innerPadding: PaddingValues, viewModel: AppViewModel, searchQuery: Map<String,Any>) {
    val artist by viewModel.artist.collectAsState()
    val recommendations by viewModel.recommendations.collectAsState()
    val uriHandler = LocalUriHandler.current
    var selectedGenres = ""

    val genres = searchQuery["genres"]
    if (genres is List<*>) {
        selectedGenres = genres.filterIsInstance<Pair<String, MutableState<Boolean>>>()
            .filter { it.second.value }.joinToString(",") { it.first }
    }

    LaunchedEffect(Unit) {
        if (searchQuery["artist"].toString() != "") {
            viewModel.getArtist(searchQuery["artist"].toString())
            if (artist.artists.results.isNotEmpty() && selectedGenres != "") {
                val artistId = artist.artists.results[0].typeDetails.artist.id
                if (artistId != "") {
                    val body = mapOf(
                        "limit" to 20,
                        "offset" to 0,
                        "artists" to mapOf(
                            "useTop" to false,
                            "seed" to artistId
                        ),
                        "genres" to mapOf(
                            "useTop" to false,
                            "seed" to selectedGenres
                        )
                    )
                    viewModel.getRecommendations(body)
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        item {
            Text(
                text = "Search Results",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                if (recommendations.recommendations.isNotEmpty()) {
                    for (track in recommendations.recommendations) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.9f),
                            ),
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                        ) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(120.dp)
                                ) {
                                    AsyncImage(
                                        model = track.typeDetails.album.images[0].url,
                                        contentDescription = "Album cover",
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                ) {
                                    Text(
                                        text = track.typeDetails.track.name,
                                        color = Color.Black,
                                        fontSize = 22.sp,
                                        fontFamily = quicksandFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = track.typeDetails.artists[0].name,
                                        color = Color.Gray,
                                        fontSize = 17.sp,
                                        fontFamily = quicksandFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        modifier = Modifier
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.spotify_logo),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(20.dp)
                                                .clickable {
                                                    uriHandler.openUri(track.typeDetails.track.externalUrls.spotify)
                                                }
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            modifier = Modifier
                                                .clickable {
                                                    uriHandler.openUri(track.typeDetails.track.externalUrls.spotify)
                                                },
                                            text = "Listen on Spotify",
                                            color = Color.Gray,
                                            fontSize = 16.sp,
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
}