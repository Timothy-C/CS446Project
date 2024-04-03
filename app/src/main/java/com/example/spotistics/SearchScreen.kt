package com.example.spotistics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.Purple
import com.example.spotistics.ui.theme.quicksandFamily

@Composable
fun Search(innerPadding: PaddingValues, colScrollState: LazyListState, onItemClick: (NavigationItem) -> Unit) {
    val albumText = remember { mutableStateOf(TextFieldValue()) }
    val artistText = remember { mutableStateOf(TextFieldValue()) }
    val genreText = remember { mutableStateOf(TextFieldValue()) }
    val yearStartText = remember { mutableStateOf(TextFieldValue()) }
    val yearEndText = remember { mutableStateOf(TextFieldValue()) }

    val searchButton = NavigationItem(
        id = "results",
        title = "results",
        icon = Icons.Default.Search
    )

    LazyColumn(
        state = colScrollState,
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        item {
            Text(
                text = "Album",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = albumText.value,
                onValueChange = {
                    albumText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Artist",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = artistText.value,
                onValueChange = {
                    artistText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Genre",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = genreText.value,
                onValueChange = {
                    genreText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Year Start",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = yearStartText.value,
                onValueChange = {
                    yearStartText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Year End",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = yearEndText.value,
                onValueChange = {
                    yearEndText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .height(40.dp)
                        .width(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            onItemClick(searchButton)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Purple
                    ),
                    elevation = CardDefaults.elevatedCardElevation(10.dp),
                    shape = MaterialTheme.shapes.medium,
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Text(
                        modifier = Modifier.padding(28.dp, 7.dp, 0.dp, 0.dp),
                        text = "Search",
                        textAlign = TextAlign.Center,
                        fontSize = 19.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
}
