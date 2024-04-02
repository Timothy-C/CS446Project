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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import java.text.DateFormatSymbols
import java.util.*
import kotlin.math.ceil


@Composable
fun Statistics() {
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
                fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier.clickable { isDropDownMenuExpanded = !isDropDownMenuExpanded }
            )

            if (isDropDownMenuExpanded) {
                // Dropdown menu with calendar
                WeekSelectionDropDown(
                    initialSelectedDate = selectedWeek,
                    onWeekSelected = { startDate, endDate ->
                        // Handle selected week
                        selectedWeek = startDate // For demonstration, you can update the selectedWeek here
                        isDropDownMenuExpanded = false
                    },
                    onDismiss = { isDropDownMenuExpanded = false }
                )
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
            modifier = Modifier.padding(top = 16.dp)
        )

        val items = listOf(
            "Acousticness" to 7,
            "Danceability" to 8,
            "Energy" to 6,
            "Instrumentalness" to 4,
            "Liveness" to 5,
            "Popularity" to 7,
            "Speechiness" to 5
        )

//        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp)
//                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                items.forEach { (itemName, value) ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Item name
                        Text(
                            text = itemName,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        // Slider
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Slider(
                                value = value.toFloat(),
                                onValueChange = { /* Handle value change */ },
                                valueRange = 1f..10f,
                                steps = 9,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }


            // Vertical scrollbar
//            val scrollbarAdapter = rememberScrollbarAdapter(scrollState)
//            VerticalScrollbar(
//                adapter = scrollbarAdapter,
//                modifier = Modifier.fillMaxHeight()
//            )
        }
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

fun getDaysInMonth(calendar: Calendar): Int {
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun getFirstDayOfMonth(calendar: Calendar): Int {
    val clonedCalendar = calendar.clone() as Calendar
    clonedCalendar.set(Calendar.DAY_OF_MONTH, 1)
    return clonedCalendar.get(Calendar.DAY_OF_WEEK)
}

@Composable
fun CalendarGrid(
    selectedDate: Calendar,
    onDateSelected: (selectedDate: Calendar) -> Unit,
    isWithinSelectedWeek: (date: Calendar) -> Boolean
) {
    val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")
    val daysInMonth = getDaysInMonth(selectedDate)
    val firstDayOfMonth = getFirstDayOfMonth(selectedDate)
    val startingOffset = firstDayOfMonth - 1

    // Calculate number of rows needed for the calendar grid
    val numRows = ceil((daysInMonth + startingOffset) / 7.0).toInt()

    // Display the calendar grid
    Column {
        // Days of the week headers
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            for (day in daysOfWeek) {
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Calendar grid cells
        for (row in 0 until numRows) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                for (col in 0 until 7) {
                    val dayIndex = row * 7 + col - startingOffset
                    if (dayIndex >= 0 && dayIndex < daysInMonth) {
                        val day = dayIndex + 1
                        val cal = selectedDate.clone() as Calendar
                        cal.set(Calendar.DAY_OF_MONTH, day)
                        val isWithinSelectedWeek = isWithinSelectedWeek(cal)
                        val isSelectedDay = isWithinSelectedWeek && cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isSelectedDay) Color.Green else Color.Transparent)
                                .clickable { onDateSelected(cal) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.toString(),
                                color = if (isSelectedDay) Color.White else if (isWithinSelectedWeek) Color.Black else Color.Gray,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        // Empty cell for offset days
                        Box(modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun WeekSelectionDropDown(
    initialSelectedDate: Calendar,
    onWeekSelected: (startDate: Calendar, endDate: Calendar) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedStartDate by remember { mutableStateOf(initialSelectedDate) }
    var selectedEndDate by remember { mutableStateOf(initialSelectedDate.clone() as Calendar) }

    // Function to update selected week range and dismiss the dropdown
    fun selectWeekAndDismiss() {
        onWeekSelected(selectedStartDate, selectedEndDate)
        onDismiss()
    }

    // Function to navigate to previous month
    fun navigateToPreviousMonth() {
        selectedStartDate.add(Calendar.MONTH, -1)
        selectedEndDate = selectedStartDate.clone() as Calendar
    }

    // Function to navigate to next month
    fun navigateToNextMonth() {
        selectedStartDate.add(Calendar.MONTH, 1)
        selectedEndDate = selectedStartDate.clone() as Calendar
    }

    // Function to highlight the entire week when a day is selected
    fun highlightWeek(selectedDate: Calendar) {
        selectedStartDate = selectedDate.clone() as Calendar
        selectedStartDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        selectedEndDate = selectedDate.clone() as Calendar
        selectedEndDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
    }

    // Function to check if a given date is within the selected week range
    fun isWithinSelectedWeek(date: Calendar): Boolean {
        return date.timeInMillis in selectedStartDate.timeInMillis..selectedEndDate.timeInMillis
    }

    // Display the dropdown content
    Column(
        modifier = modifier
            .wrapContentSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Month and year display with navigation arrows
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navigateToPreviousMonth() }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Month")
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("${selectedStartDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())} ")
                    }
                    append(selectedStartDate.get(Calendar.YEAR).toString())
                }
            )
            IconButton(onClick = { navigateToNextMonth() }) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Month")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calendar grid
        CalendarGrid(
            selectedDate = selectedStartDate,
            onDateSelected = { selectedDate ->
                highlightWeek(selectedDate)
            },
            isWithinSelectedWeek = { date -> isWithinSelectedWeek(date) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Checkmark button
        Button(
            onClick = { selectWeekAndDismiss() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Done")
        }
    }
}


//@Composable
//fun CalendarView(
//    selectedDate: Calendar,
//    onDateSelected: (Calendar) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    // Store the current selected date
//    val currentSelectedDate = remember { mutableStateOf(selectedDate) }
//
//    // Function to handle selection of a date
//    val onDateClick: (Calendar) -> Unit = { date ->
//        currentSelectedDate.value = date
//        onDateSelected(date)
//    }
//
//    Column(
//        modifier = modifier
//    ) {
//        // Display month and year on top
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        ) {
//            IconButton(
//                onClick = { /* TODO: Navigate to previous month */ }
//            ) {
//                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month")
//            }
//
//            Text(
//                text = "${DateFormatSymbols().months[currentSelectedDate.value.get(Calendar.MONTH)]}, ${currentSelectedDate.value.get(Calendar.YEAR)}",
//                style = MaterialTheme.typography.subtitle1,
//                modifier = Modifier.weight(1f)
//            )
//
//            IconButton(
//                onClick = { /* TODO: Navigate to next month */ }
//            ) {
//                Icon(Icons.Default.ChevronRight, contentDescription = "Next Month")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display the grid for the days of the week
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        ) {
//            DateFormatSymbols().shortWeekdays
//                .filterIndexed { index, _ -> index > 0 }
//                .forEach { day ->
//                    Text(
//                        text = day,
//                        style = MaterialTheme.typography.subtitle2
//                    )
//                }
//        }
//
//        // Display the grid for the days of the month
//        val daysInMonth = currentSelectedDate.value.getActualMaximum(Calendar.DAY_OF_MONTH)
//        val firstDayOfMonth = currentSelectedDate.value.apply { set(Calendar.DAY_OF_MONTH, 1) }.get(Calendar.DAY_OF_WEEK)
//        val totalDaysInGrid = daysInMonth + firstDayOfMonth - 1
//
//        var currentDay = 1
//
//        Column {
//            repeat(6) { rowIndex ->
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    repeat(7) { columnIndex ->
//                        Box(
//                            modifier = Modifier
//                                .weight(1f)
//                                .aspectRatio(1f)
//                                .padding(4.dp)
//                                .clickable {
//                                    val selectedDay = currentSelectedDate.value.clone() as Calendar
//                                    selectedDay.set(Calendar.DAY_OF_MONTH, currentDay)
//                                    onDateClick(selectedDay)
//                                },
//                            contentAlignment = Alignment.Center
//                        ) {
//                            if (rowIndex == 0 && columnIndex < firstDayOfMonth - 1 ||
//                                currentDay > totalDaysInGrid
//                            ) {
//                                // Empty cells before the first day of the month or after the last day of the month
//                                // No need to display anything
//                            } else {
//                                // Display the day of the month
//                                Text(
//                                    text = if (currentDay <= daysInMonth) currentDay.toString() else "",
//                                    style = MaterialTheme.typography.body1.copy(
//                                        color = if (currentDay == selectedDate.get(Calendar.DAY_OF_MONTH))
//                                            Color.White
//                                        else
//                                            Color.Black
//                                    ),
//                                    modifier = Modifier
//                                        .padding(8.dp)
//                                        .background(
//                                            if (currentDay == selectedDate.get(Calendar.DAY_OF_MONTH))
//                                                MaterialTheme.colors.primary
//                                            else
//                                                Color.Transparent,
//                                            shape = CircleShape
//                                        )
//                                )
//                                currentDay++
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


@Preview
@Composable
fun ListeningStatisticsPreview() {
    Statistics()
}

//@Preview
//@Composable
//fun CalendarPreview() {
//    Box(
//        modifier = Modifier
//            .padding(16.dp)
//            .background(Color.Gray)
//    ) {
//        val selectedDate = Calendar.getInstance()
//        WeekSelectionDropDown(
//            initialSelectedDate = selectedDate,
//            onWeekSelected = { _, _ -> },
//            onDismiss = {},
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}


//@Preview
//@Composable
//fun CalendarViewPreview() {
//    // Initialize a Calendar instance with the current date
//    val selectedDate = Calendar.getInstance()
//
//    // Display the CalendarView with the selectedDate
//    CalendarView(selectedDate = selectedDate, onDateSelected = {})
//}

//@Composable
//fun WeekSelectionDropDown(
//    onDateSelected: (Calendar) -> Unit,
//    selectedDate: Calendar
//) {
//    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
//    var selectedWeek by remember { mutableStateOf(Calendar.getInstance()) }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        ClickableText(
//            text = buildAnnotatedString {
//                withStyle(
//                    style = TextStyle(
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        fontFamily = quicksandFamily,
//                        fontWeight = FontWeight.Light
//                    )
//                ) {
//                    append("Select Week")
//                }
//            },
//            onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded }
//        )
//
//        if (isDropDownMenuExpanded) {
//            // Dropdown menu with calendar
////            Popup {
////                CalendarView(selectedWeek) { newDate ->
////                    selectedWeek = newDate
////                    isDropDownMenuExpanded = false
////                    onDateSelected(newDate)
////                }
////            }
//            WeekSelectionDropDown(
//                selectedDate = selectedWeek,
//                onDateSelected = { newDate ->
//                    selectedWeek = newDate
//                    isDropDownMenuExpanded = false
//                    onDateSelected(newDate)
//                }
//            )
//        }
//    }
//}