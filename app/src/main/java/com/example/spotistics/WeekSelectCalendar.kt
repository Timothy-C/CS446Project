package com.example.spotistics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Locale
import kotlin.math.ceil

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
