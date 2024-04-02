package com.example.spotistics

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.quicksandFamily
import recSongs

@Composable
fun Home(innerPadding: PaddingValues, colScrollState: LazyListState, applicationContext: Context) {
    val rowScrollState = rememberScrollState()

    LazyColumn(
        state = colScrollState,
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
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
                    .height(170.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.featured),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

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
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.album1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.album2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.album3),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.album4),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

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
                for (i in 1..5) {
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
                                    model = recSongs[i][2],
                                    contentDescription = "Album cover",
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                            ) {
                                androidx.compose.material3.Text(
                                    text = recSongs[i][1],
                                    color = Color.Black,
                                    fontSize = 22.sp,
                                    fontFamily = quicksandFamily,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                androidx.compose.material3.Text(
                                    text = recSongs[i][3],
                                    color = Color.Gray,
                                    fontSize = 17.sp,
                                    fontFamily = quicksandFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}
