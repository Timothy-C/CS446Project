package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.quicksandFamily
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.RadioButton


enum class FilterOption {
    Artists, Genres, Tracks
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recommendations(innerPadding: PaddingValues, colScrollState: LazyListState, songs: List<Song>) {
    var songsToShow by remember { mutableStateOf(songs) }

    Column(
        modifier = Modifier.padding(30.dp, 15.dp, 30.dp, 0.dp)
    ) {
        Text(
            text = "Recommendations",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(18.dp))
        // This would be a placeholder for your recommendation image
        Card(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.recommendations),
                    contentDescription = "Recommendation Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Column(
                    modifier = Modifier
                        .background(Color(0x80F8F4F4))
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Songs to sing in the shower",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Scaffold { innerPadding ->
            CollapsibleFiltersSection(
                songs = songsToShow,
                updateSongList = { updatedSongs ->
                    songsToShow = updatedSongs
                }
            )
        }
    }
}


@Composable
fun ArtistRangeDropdown(selectedRange: String, onRangeSelected: (String) -> Unit) {
    val options = listOf("short_term", "medium_term", "long_term")
    var expanded by remember { mutableStateOf(false) }

    Box {
        Text(text = selectedRange, modifier = Modifier.clickable { expanded = true })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { range ->
                DropdownMenuItem(
                    onClick = {
                        onRangeSelected(range)
                        expanded = false
                    }
                ) {
                    Text(range)
                }
            }
        }
    }
}

fun Float.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun FilterSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f, // Default range, can be overridden
    enabled: Boolean,
    onIncludeChange: (Boolean) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = enabled, onCheckedChange = onIncludeChange)
            // Showing the formatted value next to the label
            Text(text="$label: ${value.toInt()}", color = Color.Black)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            enabled = enabled
        )
    }
}
@Composable
fun FilterSliderWithSteps(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    enabled: Boolean,
    onIncludeChange: (Boolean) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = enabled, onCheckedChange = onIncludeChange)
            Text(text="$label: ${value.format(1)}", color = Color.Black) // Display the label and value with one decimal
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9, // 10 discrete values: 0, 0.1, 0.2, ..., 0.9, 1.0
            enabled = enabled
        )
    }
}
@Composable
fun SongItem(song: Song, textColor: Color = Color.Black) {
    var isLiked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = song.coverResourceId),
            contentDescription = "Cover Image",
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.weight(1f) // This ensures the column takes up most of the row space, pushing the IconButton to the end
        ) {
            Text(
                text = song.title,
                color = textColor,
                fontSize = 20.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = song.artist,
                color = textColor,
                fontSize = 15.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Normal
            )
        }
        // Toggle the isLiked state on click
        IconButton(onClick = { isLiked = !isLiked }) {
            androidx.compose.material.Icon(
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Like",
                tint = if (isLiked) Color.Red else Color.Gray // Optional: Change the color when liked
            )
        }
        IconButton(onClick = { /* Implement action for more options here */ }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More Options",
                tint = textColor
            )
        }
    }
}

@Composable
fun CollapsibleFiltersSection(songs: List<Song>, updateSongList: (List<Song>) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var selectedOption by remember { mutableStateOf(FilterOption.Artists) } // Default to 'Artists'


    var limit by remember { mutableStateOf(10) }
    var artists by remember { mutableStateOf(false) }
    var genres by remember { mutableStateOf(false)  }
    var tracks by remember { mutableStateOf(false)  }

    var acousticness by remember { mutableStateOf(0.5f) }
    var acousticnessInclude by remember { mutableStateOf(false) }
    var danceability by remember { mutableStateOf(0.5f) }
    var danceabilityInclude by remember { mutableStateOf(false) }
    var duration by remember { mutableStateOf(60000f) }
    var durationInclude by remember { mutableStateOf(false) }
    var energy by remember { mutableStateOf(0.5f) }
    var energyInclude by remember { mutableStateOf(false) }
    var instrumentalness by remember { mutableStateOf(0.5f) }
    var instrumentalnessInclude by remember { mutableStateOf(false) }
    var liveness by remember { mutableStateOf(0.5f) }
    var livenessInclude by remember { mutableStateOf(false) }
    var loudness by remember { mutableStateOf(-6f) }
    var loudnessInclude by remember { mutableStateOf(false) }
    var mode by remember { mutableStateOf(1.0f) }
    var modeInclude by remember { mutableStateOf(false) }
    var popularity by remember { mutableStateOf(80f) }
    var popularityInclude by remember { mutableStateOf(false) }
    var speechiness by remember { mutableStateOf(0.5f) }
    var speechinessInclude by remember { mutableStateOf(false) }
    var tempo by remember { mutableStateOf(160f) }
    var tempoInclude by remember { mutableStateOf(false) }
    var valence by remember { mutableStateOf(0.5f) }
    var valenceInclude by remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (isExpanded) "Hide Filters" else "Show Filters",
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        AnimatedVisibility(visible = isExpanded) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
            ) {
                // Limit Input
                OutlinedTextField(
                    value = limit.toString(),
                    onValueChange = { newValue ->
                        // Update limit with the new value if it's an integer and less than or equal to 30,
                        // otherwise set it to 0
                        limit = newValue.toIntOrNull()?.takeIf { it <= 30 } ?: 0
                    },
                    label = {
                        Text(
                            "Limit (up to 30)",
                            color = Color.Black
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(color = Color.Black), // Set the color of the input text here

                )

                Row(Modifier.fillMaxWidth()) {
                    RadioOption(option = FilterOption.Artists, selectedOption = selectedOption, onOptionSelected = { selectedOption = it })
                    RadioOption(option = FilterOption.Genres, selectedOption = selectedOption, onOptionSelected = { selectedOption = it })
                    RadioOption(option = FilterOption.Tracks, selectedOption = selectedOption, onOptionSelected = { selectedOption = it })
                }


                FilterSliderWithSteps(
                    label = "Acousticness",
                    value = acousticness,
                    onValueChange = { newValue -> acousticness = newValue },
                    enabled = acousticnessInclude,
                    onIncludeChange = { newValue -> acousticnessInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Danceability",
                    value = danceability,
                    onValueChange = { newValue -> danceability = newValue },
                    enabled = danceabilityInclude,
                    onIncludeChange = { newValue -> danceabilityInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Energy",
                    value = energy,
                    onValueChange = { newValue -> energy = newValue },
                    enabled = energyInclude,
                    onIncludeChange = { newValue -> energyInclude = newValue }
                )
                FilterSlider(
                    label = "Duration",
                    value = duration,
                    onValueChange = { newValue -> duration = newValue },
                    valueRange = 0f..180000f, // Set the custom range for duration
                    enabled = durationInclude,
                    onIncludeChange = { newValue -> durationInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Instrumentalness",
                    value = instrumentalness,
                    onValueChange = { newValue -> instrumentalness = newValue },
                    enabled = instrumentalnessInclude,
                    onIncludeChange = { newValue -> instrumentalnessInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Liveness",
                    value = liveness,
                    onValueChange = { newValue -> liveness = newValue },
                    enabled = livenessInclude,
                    onIncludeChange = { newValue -> livenessInclude = newValue }
                )
                FilterSlider(
                    label = "Loudness",
                    value = loudness,
                    onValueChange = { newValue -> loudness = newValue },
                    valueRange = -60f..0f,
                    enabled = loudnessInclude,
                    onIncludeChange = { newValue -> loudnessInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Mode",
                    value = mode,
                    onValueChange = { newValue -> mode = newValue },
                    enabled = modeInclude,
                    onIncludeChange = { newValue -> modeInclude = newValue }
                )
                FilterSlider(
                    label = "Popularity",
                    value = popularity,
                    onValueChange = { newValue -> popularity = newValue },
                    valueRange = 0f..100f, // Set the custom range for duration
                    enabled = popularityInclude,
                    onIncludeChange = { newValue -> popularityInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Speechiness",
                    value = speechiness,
                    onValueChange = { newValue -> speechiness = newValue },
                    enabled = speechinessInclude,
                    onIncludeChange = { newValue -> speechinessInclude = newValue }
                )
                FilterSlider(
                    label = "Tempo",
                    value = tempo,
                    onValueChange = { newValue -> tempo = newValue },
                    valueRange = 0f..1000f, // Set the custom range for duration
                    enabled = tempoInclude,
                    onIncludeChange = { newValue -> tempoInclude = newValue }
                )
                FilterSliderWithSteps(
                    label = "Valence",
                    value = valence,
                    onValueChange = { newValue -> valence = newValue },
                    enabled = valenceInclude,
                    onIncludeChange = { newValue -> valenceInclude = newValue }
                )

                Button(
                    onClick = {
                        updateSongList(dummySongs3)

                        // Reset error message at the beginning of the logic
                        errorMessage = null

                        val activeFilters = listOf(
                            acousticnessInclude, danceabilityInclude,
                            durationInclude, energyInclude, instrumentalnessInclude,
                            livenessInclude, loudnessInclude, modeInclude, popularityInclude,
                            speechinessInclude, tempoInclude, valenceInclude
                        ).count { it }

                        // Validate total active filters
                        if (activeFilters > 9) {
                            errorMessage = "Selected items should not be greater than 10"
                        } else {
                            // If there are no errors, apply filters, hide the filter section, and show the list of songs
//                            filtersApplied = true
                            isExpanded = false // Optionally collapse the filter section
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Apply Filters")
                }

                // Conditionally display the error message
                errorMessage?.let {
                    Text(
                        it,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Divider(Modifier.padding(vertical = 8.dp))

        // List of songs (assuming LazyColumn for simplicity)
        LazyColumn {
            items(songs) { song ->
                SongItem(song = song) // Your existing SongItem composable
                Divider()
            }
        }
    }
}

@Composable
fun RadioOption(option: FilterOption, selectedOption: FilterOption, onOptionSelected: (FilterOption) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 1.dp)
            .clickable(onClick = { onOptionSelected(option) })
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) }
        )
        Text(
            text = option.name.replaceFirstChar { it.uppercase() },
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}


