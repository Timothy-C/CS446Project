package com.example.spotistics
import com.example.spotistics.ui.theme.quicksandFamily

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil



@Composable
// Function to get the default week label
fun getDefaultWeekLabel(calendar: Calendar, selectedDate: Calendar?): String {
    // Clone the provided calendar to avoid modifying its state
    val currentCalendar = calendar.clone() as Calendar

    // Set the end date to the most recent Saturday
    val endDate = currentCalendar.clone() as Calendar
    while (endDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
        endDate.add(Calendar.DAY_OF_WEEK, 1)
    }

    // Set the start date to the previous Sunday
    val startDate = endDate.clone() as Calendar
    startDate.add(Calendar.DAY_OF_WEEK, -6)

    // Format the dates into a string
    val startDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val endDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return if (selectedDate != null && selectedDate >= startDate && selectedDate <= endDate) {
        val selectedDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        "Week of ${selectedDateFormat.format(selectedDate.time)}"
    } else {
        "${startDateFormat.format(startDate.time)} - ${endDateFormat.format(endDate.time)}"
    }
}

@Composable
fun Statistics() {
    var selectedWeek by remember { mutableStateOf(Calendar.getInstance()) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Top of the screen - "Listening Statistics"
            Text(
                text = "Listening Statistics",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        val today = Calendar.getInstance()
        val mostRecentSaturday = today.clone() as Calendar
        while (mostRecentSaturday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            mostRecentSaturday.add(Calendar.DAY_OF_WEEK, -1)
        }

        item {
            // Horizontal box drop down menu for selecting week
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getDefaultWeekLabel(selectedWeek, mostRecentSaturday), // Pass null as selectedDate
                    fontSize = 20.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isDropDownMenuExpanded = !isDropDownMenuExpanded }
                        .weight(1f)
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
                        onDismiss = { isDropDownMenuExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            // Top 5 songs and Top 5 artists
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Top 5 songs
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.taylor_swift),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp) // Adjust the height to half of the original size
                        )
                    }
                    TopItemsSection(
                        title = "Top Songs",
                        items = listOf("Cruel Summer", "Glimpse of Us", "Demons", "Lover", "Thunder"),
                        titleFontSize = 20.sp,
                        titleFontFamily = quicksandFamily
                    )
                }

                // Top 5 artists
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.taylor_swift),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp) // Adjust the height to half of the original size
                        )
                    }
                    TopItemsSection(
                        title = "Top Artists",
                        items = listOf("Taylor Swift", "Imagine Dragons", "Justin Bieber", "Dua Lipa", "Joji"),
                        titleFontSize = 20.sp,
                        titleFontFamily = quicksandFamily
                    )
                }
            }
        }


        item {
            // Minutes Listened
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Minutes Listened", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
                Text(text = "Songs Listened", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
            }
        }

        item {
            // Values for Minutes Listened, Songs Listened, and Top Genre
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "521 minutes", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Minutes Listened
                Text(text = "180 songs", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Songs Listened
            }
        }

        item {
            // Top Genre
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Top Genre", color = Color.White, fontSize = 20.sp, fontFamily = quicksandFamily)
            }
        }

        item {
            // Top Genre
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Pop", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Top Genre
            }
        }

        item {
            // Subheading - "My Preferences"
            Text(
                text = "My Preferences",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        val items = listOf(
            "Acousticness" to 7,
            "Danceability" to 8,
            "Energy" to 6,
            "Instrumentalness" to 4,
            "Liveness" to 5,
            "Popularity" to 7,
            "Speechiness" to 5
        )

        items.forEach { (itemName, value) ->
            item {
                // Item name
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
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
    val daysInMonth = getDaysInMonth(selectedDate)
    val firstDayOfMonth = getFirstDayOfMonth(selectedDate)
    val startingOffset = firstDayOfMonth - 1

    // Calculate number of rows needed for the calendar grid
    val numRows = ceil((daysInMonth + startingOffset) / 7.0).toInt()

    // Display the calendar grid
    Column {

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
                                .background(if (isSelectedDay) Color.Green else Color.Transparent)
                                .clickable { onDateSelected(cal) },
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color.Transparent)
                                    .border(2.dp, Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                // Text inside the circle
                                Text(
                                    text = day.toString(),
                                    color = if (isSelectedDay) Color.White else if (isWithinSelectedWeek) Color.Black else Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
    var monthAndYearText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Function to update selected week range and dismiss the dropdown
    fun selectWeekAndDismiss() {
        if (selectedStartDate <= Calendar.getInstance()) {
            onWeekSelected(selectedStartDate, selectedEndDate)
            onDismiss()
        } else {
            showDialog = true
        }
    }

    // Function to update the month and year display
    fun updateMonthAndYearDisplay(selectedStartDate: Calendar) {
        // Manually update the month and year display
        selectedStartDate.apply {
            val monthName = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val year = get(Calendar.YEAR)
            monthAndYearText = "$monthName $year"
        }
    }

    // Initialize month and year display
    LaunchedEffect(Unit) {
        updateMonthAndYearDisplay(initialSelectedDate)
    }

    // Function to navigate to previous month
    fun navigateToPreviousMonth() {
        selectedStartDate.add(Calendar.MONTH, -1)
        selectedEndDate = selectedStartDate.clone() as Calendar
        updateMonthAndYearDisplay(selectedStartDate)
    }

    // Function to navigate to next month
    fun navigateToNextMonth() {
        selectedStartDate.add(Calendar.MONTH, 1)
        selectedEndDate = selectedStartDate.clone() as Calendar
        updateMonthAndYearDisplay(selectedStartDate)
    }

    // Function to highlight the selected date
    fun highlightSelectedDate(selectedDate: Calendar) {
        if (selectedDate <= Calendar.getInstance()) {
            selectedStartDate = selectedDate.clone() as Calendar
            selectedEndDate = selectedDate.clone() as Calendar
        } else {
            showDialog = true
        }
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
                        append(monthAndYearText)
                    }
                }
            )
            IconButton(onClick = { navigateToNextMonth() }) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Month")
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        // Calendar grid
        CalendarGrid(
            selectedDate = selectedStartDate,
            onDateSelected = { selectedDate ->
                highlightSelectedDate(selectedDate)
            },
            isWithinSelectedWeek = { date -> date.timeInMillis in selectedStartDate.timeInMillis..selectedEndDate.timeInMillis }
        )

        Spacer(modifier = Modifier.height(1.dp))

        // Checkmark button
        Button(
            onClick = { selectWeekAndDismiss() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Done")
        }

        // Dialog for future/current week date selection
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Select a day in the past") },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                    ) {
                        Text("Ok")
                    }
                }
            )
        }
    }
}



@Preview
@Composable
fun ListeningStatisticsPreview() {
    Statistics()
}
@Preview
@Composable
fun CalendarGridPreview() {
    val selectedDate = remember { Calendar.getInstance() }

    CalendarGrid(
        selectedDate = selectedDate,
        onDateSelected = {},
        isWithinSelectedWeek = { true } // Modify this based on your requirement
    )
}

@Preview
@Composable
fun WeekSelectionDropDownPreview() {
    val initialSelectedDate = Calendar.getInstance()

    // Define a sample onWeekSelected callback
    val onWeekSelected: (startDate: Calendar, endDate: Calendar) -> Unit = { startDate, endDate ->
        // Handle selected week
        // For demonstration, you can log the selected start and end dates
    }

    // Define a sample onDismiss callback
    val onDismiss: () -> Unit = {
        // Handle dismiss action
        // For demonstration, you can log the dismissal
    }

    // Preview the WeekSelectionDropDown with the provided sample data
    WeekSelectionDropDown(
        initialSelectedDate = initialSelectedDate,
        onWeekSelected = onWeekSelected,
        onDismiss = onDismiss
    )
}