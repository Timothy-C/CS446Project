@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.spotistics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.net.CronetProviderInstaller
import org.chromium.net.CronetEngine
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class StatsActivity : ComponentActivity() {

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

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    // tchan: make this work later for the album cover for the songs
    /*AsyncImage(
        model = "https://en.wikipedia.org/static/images/icons/wikipedia.png",
        contentDescription = "Translated description of what the image contains"
    )*/
    var content: String = "sadge"

    Text(
        content,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    val range = 1..50
    // tchan: replace the 1 to 50 with actual song names
    // tchan: get the song names from api (either local flask server or the actual one)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(text = "Song name ${index + 1}")
        }
    }
}