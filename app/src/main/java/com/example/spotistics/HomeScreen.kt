package com.example.spotistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.quicksandFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = { Text("Search music and recommendations") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = SearchBarDefaults.colors(),
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
    ) {
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(innerPadding: PaddingValues, colScrollState: LazyListState) {
    val rowScrollState = rememberScrollState()

    LazyColumn(
        state = colScrollState,
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        stickyHeader {
            MainSearchBar()
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Text(
                text = "Featured",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light
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
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
                    .padding(innerPadding)
            ) {
                for (i in 1..10) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .size(width = 170.dp, height = 170.dp)
                    ) {
                        Text(
                            text = "Album",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Text(
                text = "Top Recommendations",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                for (i in 1..10) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Song",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}
