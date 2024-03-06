package com.example.spotistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings(innerPadding: PaddingValues, colScrollState: LazyListState) {
    val yt = false
    val spt = false

    Column(Modifier.padding(20.dp)) {
        Button(onClick = {}) {
            Text("<")
        }
        Text(text = "settings", fontSize = 30.sp, fontWeight = FontWeight(700))
//        Text(text = "account", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))
//        Button(onClick = {}) {
//            Text("Sync recent listening history")
//        }
//        Button(onClick = {}) {
//            Text("Connect new spotify account")
//        }
        Text(text = "dark mode", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))

        Text(text = "playback", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = yt, onClick = {})
            Text("YouTube")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = spt, onClick = {})
            Text("Spotify (requires premium account)")
        }
        Text(text = "social", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Column (modifier = Modifier.padding(end = 50.dp)) {
                Text(text = "twitter", fontSize = 15.sp)
                Text(text = "share your listening stats on Twitter!")
            }
            Button(onClick = {}) {
                Text("connect")
            }
        }

        Text(text = "privacy", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "logout")
        }


    }

}