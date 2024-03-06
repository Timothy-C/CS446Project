@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.spotistics

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.spotistics.ui.theme.quicksandFamily
import com.example.spotistics.ui.theme.Navy
import com.google.android.gms.net.CronetProviderInstaller
import org.chromium.net.CronetEngine
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import androidx.compose.ui.Alignment.Horizontal

class StatsActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*      CronetProviderInstaller.installProvider(applicationContext)
              val myBuilder = CronetEngine.Builder(applicationContext)
              val cronetEngine: CronetEngine = myBuilder.build()
              val executor: Executor = Executors.newSingleThreadExecutor()
      */
        setContent {
            StatsContent()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(apiLevel = 33)
@Composable
fun StatsContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "History",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = pinnedScrollBehavior()
            )
            // tchan: add in dropdown for month selection
        },
    )
    { innerPadding ->
        ScrollContent(innerPadding)
    }
}

val history = listOf(
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Natural",
        "https://i.scdn.co/image/ab67616d0000b273da6f73a25f4c79d0e6b4a8bd",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:53:29.252Z",
        "Whatever It Takes",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:50:07.475Z",
        "Radioactive",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:46:42.157Z",
        "Enemy (with JID) - from the series Arcane League of Legends",
        "https://i.scdn.co/image/ab67616d0000b273fc915b69600dce2991a61f13",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:43:48.446Z",
        "Thunder",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:40:40.562Z",
        "Bones",
        "https://i.scdn.co/image/ab67616d0000b273fc915b69600dce2991a61f13",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:37:53.328Z",
        "Believer",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:34:28.013Z",
        "Demons",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T22:01:48.185Z",
        "Anti-Hero",
        "https://i.scdn.co/image/ab67616d0000b273bb54dde68cd23e2a268ae0f5",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-29T22:00:16.116Z",
        "Lover",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-29T21:34:34.167Z",
        "Cruel Summer",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-14T21:31:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:28:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:24:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:20:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:16:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:12:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:09:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:06:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-01-21T01:28:50.475Z",
        "Radioactive",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-01-20T06:08:56.406Z",
        "Lover",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    )
)

@RequiresApi(Build.VERSION_CODES.O)
val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

@RequiresApi(Build.VERSION_CODES.O)
val p1 = DateTimeFormatter.ofPattern("h:mma")

@RequiresApi(Build.VERSION_CODES.O)
val p2 = DateTimeFormatter.ofPattern("MMM dd, yyyy")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    // tchan: make this work later for the album cover for the songs
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(history) { index ->
            Row() {
                Box(modifier = Modifier
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
                        modifier= Modifier.align(Alignment.End)
                    )
                    Text(
                        text = LocalDateTime.parse(index[0], pattern).format(p2),
                        textAlign = TextAlign.End,
                        color = Navy,
                        fontSize = 12.sp,
                        //fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Normal,
                        modifier= Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}