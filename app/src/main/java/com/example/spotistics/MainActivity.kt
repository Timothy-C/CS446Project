package com.example.spotistics

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotistics.ui.theme.Navy
import com.example.spotistics.ui.theme.quicksandFamily
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.coroutines.launch

object Screens {
    const val Home = "home"
    const val Recommendations = "recs"
    const val Throwbacks = "throwbacks"
    const val Statistics = "stats"
    const val History = "history"
    const val Settings = "settings"
}

class MainActivity : ComponentActivity() {
    private val clientId = "dcb7c8ef25dd48c2b832fd73164d9f4c"
    private val redirectUri = "http://localhost:3000/auth/callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mainactivity", "mainactivity")
        setContent {
            actionBar?.hide()
            Navigation(viewModel = viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(navController)
        }
        composable("home") {
            MainNavigation(viewModel, navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(viewModel: MainViewModel, navController: NavHostController) {
    var route by remember {mutableStateOf(Screens.Home)}
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)
    val scrollState = rememberLazyListState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            backgroundColor = Navy,
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            drawerContent = {
                NavigationDrawer(
                    onItemClick = {
                        route = it.id
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
            },
            content = { innerPadding ->
                CompositionLocalProvider(
                    LocalLayoutDirection provides LayoutDirection.Ltr,
                ) {
                    when (route) {
                         Screens.Home -> Home(innerPadding, scrollState)
                         Screens.Recommendations -> Recommendations(innerPadding, scrollState, dummySongs)
                         Screens.Throwbacks -> Throwbacks(innerPadding, scrollState, dummySongs2)
                         Screens.Statistics -> Statistics()
                         //Screens.Statistics -> Statistics(innerPadding, scrollState)
                         Screens.History -> History(viewModel, innerPadding, scrollState)
                         Screens.Settings -> Settings(innerPadding, scrollState)
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "Spotistics",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.music_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarColors(Navy, Navy, Navy, Navy, Color.White),
        modifier = Modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(0.dp, 15.dp, 0.dp, 0.dp)
    )
}