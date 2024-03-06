package com.example.spotistics


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun Login() {
    val signInColors: ButtonColors = ButtonColors(
        containerColor = Color.Green,
        contentColor = Color.Green,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.Gray
    )

    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = Color.Black
                )
                .padding(vertical = 80.dp, horizontal = 20.dp)
        ) {
            Column(
            ) {
                Text(
                    text = "Hello,",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight(750)
                )
                Text(
                    text = "welcome to spotistics",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(450)
                )
                Text(
                    text = "Sign in With Spotify", color = Color.White, fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 150.dp)
                        .padding(vertical = 20.dp)
                )
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Button(
                        colors = signInColors, onClick = { println("sign in") },
                        modifier = Modifier.size(width = 350.dp, height = 50.dp),
                        shape = RoundedCornerShape(20),
                    ) {
                        // TODO: I present the UI
                        // TODO: make auth work by today
                        // 10.0.2.2:3000/auth/loginPageURL
                        // use spotify sdk for auth
                        // keep tokens, only send back when need request
                        Row {
                            Text("Sign in  ", color = Color.White)
                            Image(
                                painterResource(id = R.drawable.spotify),
                                "x",
                            )
                            Text("  ->", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}