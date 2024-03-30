package com.example.spotistics
import com.example.spotistics.ui.theme.quicksandFamily

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.window.Popup

import java.util.*


@Composable
fun ListeningStatisticsScreen() {
    var selectedWeek by remember { mutableStateOf(Calendar.getInstance()) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top of the screen - "Listening Statistics"
        Text(
            text = "Listening Statistics",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Horizontal box drop down menu for selecting week
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select Week",
                fontSize = 20.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light,                color = Color.White,
                modifier = Modifier.clickable { isDropDownMenuExpanded = !isDropDownMenuExpanded }
            )

            if (isDropDownMenuExpanded) {
                // Dropdown menu with calendar
                Popup {
                    CalendarView(selectedWeek) { newDate ->
                        selectedWeek = newDate
                        isDropDownMenuExpanded = false
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                // Column for top song
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Image of the top song, shrink to half size
                    Image(
                        painter = painterResource(id = R.drawable.taylor_swift),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp) // Adjust the height to half of the original size
                    )
                    // Top 5 songs
                    TopItemsSection(
                        title = "Top Songs",
                        items = listOf("Song 1", "Song 2", "Song 3", "Song 4", "Song 5"),
                        titleFontSize = 20.sp,
                        titleFontFamily = quicksandFamily
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                // Column for top artist
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Image of the top artist, shrink to half size
                    Image(
                        painter = painterResource(id = R.drawable.taylor_swift),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp) // Adjust the height to half of the original size
                    )
                    // Top 5 artists
                    TopItemsSection(
                        title = "Top Artists",
                        items = listOf("Artist 1", "Artist 2", "Artist 3", "Artist 4", "Artist 5"),
                        titleFontSize = 20.sp,
                        titleFontFamily = quicksandFamily
                    )
                }
            }
        }

        // Minutes Listened
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Minutes Listened", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
            Text(text = "Songs Listened", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
        }

        // Values for Minutes Listened, Songs Listened, and Top Genre
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "7,000 minutes", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Minutes Listened
            Text(text = "220 songs", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Songs Listened
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Top Genre", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
        }

        // Values for Minutes Listened, Songs Listened, and Top Genre
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Pop", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Top Genre
        }

        // Subheading - "My Preferences"
        Text(
            text = "My Preferences",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
fun TopItemsSection(
    title: String,
    items: List<String>,
    titleFontSize: TextUnit = 20.sp,
    titleFontFamily: FontFamily = quicksandFamily
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        // Title
        Text(
            text = title,
            fontSize = titleFontSize,
            fontFamily = titleFontFamily,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.White
        )

        // Top items list
        items.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = "${index + 1}. ", color = Color.White)
                Text(text = item, color = Color.White)
            }
        }
    }
}


@Composable
fun CalendarView(selectedDate: Calendar, onDateSelected: (Calendar) -> Unit) {
    // Dummy calendar view for now
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(Color.White)
    ) {
        Text(
            text = "Calendar View",
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun ListeningStatisticsPreview() {
    ListeningStatisticsScreen()
}
