package com.example.spotistics


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spotistics.ui.theme.Navy
import com.example.spotistics.ui.theme.quicksandFamily


@Composable
fun Login(navController: NavHostController, link: () -> Unit) {
    val signInColors = ButtonColors(
        containerColor = Color.Green,
        contentColor = Color.Green,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.Gray
    )

    Scaffold(
        backgroundColor = Color.Transparent,
        modifier = Modifier.background(Brush.verticalGradient(listOf(Navy, Color.Black))),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(30.dp, 50.dp, 30.dp, 0.dp)
            ) {
                Text(
                    text = "Hello,",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Welcome to Spotistics",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Sign in with Spotify",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 150.dp)
                        .padding(vertical = 20.dp)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(innerPadding)
                ) {
                    Button(
                        colors = signInColors,
                        onClick = {
                            link()
                            navController.navigate("home")
                        },
                        modifier = Modifier.size(width = 350.dp, height = 50.dp),
                        shape = RoundedCornerShape(20),
                    ) {
                        // TODO: I present the UI
                        // TODO: make auth work by today
                        // 10.0.2.2:3000/auth/loginPageURL
                        // use spotify sdk for auth
                        // keep tokens, only send back when need request
                        Row {
                            Text(
                                text = "Sign in  ",
                                color = Color.Black,
                                fontFamily = quicksandFamily,
                                fontWeight = FontWeight.Normal
                            )
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
    )
}
