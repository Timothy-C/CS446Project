package com.example.spotistics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun Search(innerPadding: PaddingValues, onItemClick: (NavigationItem) -> Unit) {
    val artistText = remember { mutableStateOf(TextFieldValue("")) }
    val checkboxStates = genres.map { it to mutableStateOf(false) }

    // Create search button
    val searchButton = NavigationItem(
        id = "results",
        title = "results",
        icon = Icons.Default.Search
    )

    // Display user input form
    LazyColumn(
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        // Allow user to enter their preferred artist
        item {
            Text(
                text = "Favourite Artist",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            TextField(
                value = artistText.value,
                onValueChange = { artistText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Allow user to select their preferred genres
        item {
            Text(
                text = "Genres",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                for (item in checkboxStates) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = item.second.value,
                            onCheckedChange = { item.second.value = it },
                            colors = CheckboxDefaults.colors(uncheckedColor = Color.Black)
                        )
                        Text(
                            text = item.first,
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

        // Display search button and pass artist and genre data to the results screen
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
                            searchButton.data = mapOf(
                                "artist" to artistText.value.text,
                                "genres" to checkboxStates
                            )
                            onItemClick(searchButton)
                        },
                    colors = CardDefaults.cardColors(containerColor = Purple),
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
