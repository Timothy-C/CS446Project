package com.example.spotistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.quicksandFamily
import searchSongs

@Composable
fun SearchResults(innerPadding: PaddingValues, colScrollState: LazyListState, /*searchQuery: HashMap<String,String>*/) {
    LazyColumn(
        state = colScrollState,
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
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                for (i in 0..4) {
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
                                    model = searchSongs[i][2],
                                    contentDescription = "Album cover",
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                            ) {
                                Text(
                                    text = searchSongs[i][1],
                                    color = Color.Black,
                                    fontSize = 22.sp,
                                    fontFamily = quicksandFamily,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = searchSongs[i][3],
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