@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.spotistics

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.Navy
import com.example.spotistics.ui.theme.quicksandFamily
import history
import historydata
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(apiLevel = 33)
//@Composable
//fun HistoryScreen(innerPadding: PaddingValues, colScrollState: LazyListState) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text(
//                        "History",
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                },
//                scrollBehavior = pinnedScrollBehavior()
//            )
//            // tchan: add in dropdown for month selection
//        },
//    )
//    { innerPadding ->
//        ScrollContent(innerPadding)
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

@RequiresApi(Build.VERSION_CODES.O)
val p1 = DateTimeFormatter.ofPattern("h:mma")

@RequiresApi(Build.VERSION_CODES.O)
val p2 = DateTimeFormatter.ofPattern("MMM dd, yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun History(innerPadding: PaddingValues, viewModel: HistoryViewModel) {
    //val songhistory by viewModel.songs.collectAsState()
    var excludeStart: Long by remember { mutableLongStateOf( java.time.Instant.now().toEpochMilli()) }
    var excludeEnd: Long by remember { mutableLongStateOf(0) }

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))
    val state = rememberDateRangePickerState()
    //val snackScope = rememberCoroutineScope()
    var history2 by remember { mutableStateOf(historydata.toList()) }

    val viewmodelsonghistory by viewModel.songs
    // exclude the songs that are within the timeline
    history = viewmodelsonghistory.map { listOf(it.timeStamp, it.coverResource, it.title, it.artist) }.toMutableList()

    //val historyResults by viewModel.history.collectAsState()

    //LaunchedEffect(Unit) {
    //    viewModel.getHistory(startDate.timeInMillis, endDate.timeInMillis, 20, 0)
    //}

    Column(
        modifier = Modifier
            .padding(30.dp, 15.dp, 30.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            text = "History",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Light
        )
        // Horizontal box drop down menu for selecting week
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select Range",
                fontSize = 20.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Light,                color = Color.White,
                modifier = Modifier.clickable { isDropDownMenuExpanded = !isDropDownMenuExpanded }
            )

            if (isDropDownMenuExpanded) {
                // Dropdown menu with calendar
                Popup {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.Top
                    ) {
                        // Add a row with "Save" and dismiss actions. .background(DatePickerDefaults.colors().containerColor)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp, end = 12.dp)
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = { /* dismiss the UI */
                                isDropDownMenuExpanded = !isDropDownMenuExpanded
                            }) {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = "Localized description"
                                )
                            }
                            TextButton(
                                onClick = {
                                    excludeStart = state.selectedStartDateMillis!!
                                    excludeEnd = state.selectedEndDateMillis!!
                                    //history2.removeIf { Date.from(LocalDate.parse(it[0].substring(0, 10)).atStartOfDay(ZoneId.systemDefault()).toInstant()).time in excludeStart..excludeEnd }
                                    val history3 = mutableListOf<List<String>>()
                                    for (day in historydata){
                                        val date = Date.from(LocalDate.parse(day[0].substring(0, 10)).atStartOfDay(ZoneId.systemDefault()).toInstant()).time
                                        if (date < excludeStart || excludeEnd < date) {
                                            Log.d("TAG", "Remove ${historydata.indexOf(day)}")
                                            history3.add(day)
                                        }
                                        else{
                                            Log.d("TAG", "Keep ${historydata.indexOf(day)}")
                                        }
                                    }
                                    history2 = history3
                                    isDropDownMenuExpanded = !isDropDownMenuExpanded
                                },
                                enabled = state.selectedEndDateMillis != null
                            ) {
                                Text(text = "Save")
                            }
                        }
                        DateRangePicker(state = state, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))


        LazyColumn(
            modifier = Modifier.background(Color.White),
        ) {
            items(history2) { index ->
                Row() {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            model = index[2],
                            contentDescription = "Translated description of what the image contains",
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(4f)
                            .padding(8.dp),
                    ) {
                        Text(
                            text = index[1],
                            color = Navy,
                            fontSize = 16.sp,
                            //fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = index[3],
                            color = Color.Gray,
                            fontSize = 12.sp,
                            //fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(8.dp),
                    ) {
                        Text(
                            text = LocalDateTime.parse(index[0], pattern).format(p1),
                            textAlign = TextAlign.End,
                            color = Navy,
                            fontSize = 12.sp,
                            //fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.align(Alignment.End)
                        )
                        Text(
                            text = LocalDateTime.parse(index[0], pattern).format(p2),
                            textAlign = TextAlign.End,
                            color = Navy,
                            fontSize = 12.sp,
                            //fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }
    }
    //DisposableEffect(Unit) {
        //viewModel.getHistory()
        //onDispose {}
    //}
}
