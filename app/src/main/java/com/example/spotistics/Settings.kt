package com.example.spotistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings(innerPadding: PaddingValues) {
    val yt = false
    val spt = false

    Column(Modifier.padding(20.dp)) {
        Button(onClick = {}) {
            Text("<")
        }
        Text(
            text = "Settings",
            fontSize = 30.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        )
//        Text(text = "account", fontSize = 20.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top = 20.dp))
//        Button(onClick = {}) {
//            Text("Sync recent listening history")
//        }
//        Button(onClick = {}) {
//            Text("Connect new spotify account")
//        }
        Text(
            text = "Dark Mode",
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 20.dp),
            color = Color.White
        )

        Text(
            text = "playback",
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 20.dp),
            color = Color.White
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = yt,
                onClick = {},
                colors = RadioButtonDefaults.colors(unselectedColor = Color.White)
            )
            Text(
                text = "YouTube",
                color = Color.White
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = spt,
                onClick = {},
                colors = RadioButtonDefaults.colors(unselectedColor = Color.White)
            )
            Text(
                text = "Spotify (requires premium account)",
                color = Color.White
            )
        }
        Text(
            text = "Social",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 20.dp)
        )
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Column (modifier = Modifier.padding(end = 50.dp)) {
                Text(
                    text = "twitter",
                    fontSize = 15.sp,
                    color = Color.White
                )
                Text(
                    text = "share your listening stats on Twitter!",
                    color = Color.White
                )
            }
            Button(onClick = {}) {
                Text(
                    text = "connect",
                    color = Color.White
                )
            }
        }

        Text(
            text = "privacy",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 20.dp)
        )
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "logout",
                color = Color.White
            )
        }
    }
}