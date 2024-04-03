package com.example.spotistics

//import androidx.compose.foundation.VerticalScrollbar
//import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.quicksandFamily
import java.util.Calendar
import java.util.Locale
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.random.Random



val targetStartDate = Calendar.getInstance().apply {
    set(2024, Calendar.MARCH, 17)
}
// Mock functions for API calls
fun getTopSongs(startDate: Calendar, endDate: Calendar): List<String> {
    // Implement logic to fetch top songs data from API based on selected week
    val songs = listOf("Lover", "Cruel Summer", "Paradise", "Dandelions", "Demons", "Photograph", "Fix You", "Happier")

    val shuffledSongs = songs.shuffled(Random.Default) // Shuffle the list of artists
    val selectedSongs = mutableListOf<String>()

    if ("Cruel Summer" in shuffledSongs) {
        selectedSongs.add("Cruel Summer")
        shuffledSongs.filterNot { it == "Taylor Swift" } // Remove "Taylor Swift" from the shuffled list
            .take(4) // Take the next 4 elements from the shuffled list
            .forEach { selectedSongs.add(it) } // Add them to the selected list
    }
    return selectedSongs
}

fun getTopArtists(startDate: Calendar, endDate: Calendar): List<String> {
    // Implement logic to fetch top artists data from API based on selected week
    val artists = listOf("Taylor Swift", "Shawn Mendes", "Coldplay", "Ed Sheeran", "Imagine Dragons", "Ariana Grande", "Justin Bieber", "Joji", "Olivia Rodrigo")

    val shuffledArtists = artists.shuffled(Random.Default) // Shuffle the list of artists
    val selectedArtists = mutableListOf<String>()

    // Ensure "Taylor Swift" is in the selected list
    if ("Taylor Swift" in shuffledArtists) {
        selectedArtists.add("Taylor Swift")
        shuffledArtists.filterNot { it == "Taylor Swift" } // Remove "Taylor Swift" from the shuffled list
            .take(4) // Take the next 4 elements from the shuffled list
            .forEach { selectedArtists.add(it) } // Add them to the selected list
    }

    return selectedArtists
}

fun getMinutesListened(startDate: Calendar, endDate: Calendar): Int {
    // Implement logic to fetch minutes listened data from API based on selected week
    return Random.nextInt(500, 999)

}

fun getSongsListened(startDate: Calendar, endDate: Calendar): Int {
    // Implement logic to fetch songs listened data from API based on selected week
    return Random.nextInt(120, 250)
}

fun getGenre(startDate: Calendar, endDate: Calendar): String {
    // Implement logic to fetch genre data from API based on selected week
    return "Pop"
}

// data class to represent a preference
data class Preference(
    val name: String,
    val value: Int
)

val preferences = listOf(
    Preference("Acousticness", 7),
    Preference("Danceability", 8),
    Preference("Energy", 6),
    Preference("Instrumentalness", 4),
    Preference("Liveness", 5),
    Preference("Popularity", 7),
    Preference("Speechiness", 5)
)

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

fun getDefaultWeekDates(calendar: Calendar): Pair<Calendar, Calendar> {
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

    return Pair(startDate, endDate)
}

@Composable
fun Statistics() {
    var selectedWeek by remember { mutableStateOf(Calendar.getInstance()) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val (startDate, endDate) = getDefaultWeekDates(calendar)

    // API calls to fetch data based on selected week
    val topSongs = remember { getTopSongs(startDate, endDate) }
    val topArtists = remember { getTopArtists(startDate, endDate) }
//    val minutesListened = remember { getMinutesListened(startDate, endDate) }
//    val songsListened = remember { getSongsListened(startDate, endDate) }
    val genre = remember { getGenre(startDate, endDate) }

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
                        items = getTopSongs(startDate, endDate),
//                        items = listOf("Cruel Summer", "Glimpse of Us", "Demons", "Lover", "Thunder"),
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
                        items = getTopArtists(startDate, endDate),
//                        items = listOf("Taylor Swift", "Imagine Dragons", "Justin Bieber", "Dua Lipa", "Joji"),
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
            val minutesListened = remember { getMinutesListened(startDate, endDate) }
            val songsListened = remember { getSongsListened(startDate, endDate) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "$minutesListened minutes", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Minutes Listened
                Text(text = "$songsListened songs", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Songs Listened
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
                Text(text = "$genre", color = Color.White, fontSize = 16.sp, fontFamily = quicksandFamily) // Top Genre
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

        preferences.forEach { preference ->
            item {
                // Preference name
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = preference.name,
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
                            value = preference.value.toFloat(),
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
