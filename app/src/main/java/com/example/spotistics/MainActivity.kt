package com.example.spotistics

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotistics.ui.theme.Navy
import com.example.spotistics.ui.theme.quicksandFamily
import com.google.android.material.snackbar.Snackbar
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object Screens {
    const val Home = "home"
    const val Recommendations = "recs"
    const val Throwbacks = "throwbacks"
    const val Statistics = "stats"
    const val History = "history"
    const val Settings = "settings"
}

class MainActivity : AppCompatActivity() {
    private val clientId = "dcb7c8ef25dd48c2b832fd73164d9f4c"
    private val mOkHttpClient = OkHttpClient()
    private var mAccessToken: String? = null
    private var mAccessCode: String? = null
    private var mCall: Call? = null
    private var spotifyAppRemote: SpotifyAppRemote? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            actionBar?.hide()
            Navigation {
                onRequestTokenClicked(null)
            }
        }
    }

    override fun onDestroy() {
        cancelCall()
        super.onDestroy()
    }

    fun onGetUserProfileClicked(view: View?) {
        if (mAccessToken == null) {
            val snackbar = Snackbar.make(
                findViewById(R.id.activity_main),
                R.string.warning_need_token,
                Snackbar.LENGTH_SHORT
            )
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            snackbar.show()
            return
        }
        val request: Request = Request.Builder()
            .url("https://api.spotify.com/v1/me")
            .addHeader("Authorization", "Bearer $mAccessToken")
            .build()
        cancelCall()
        mCall = mOkHttpClient.newCall(request)
        mCall!!.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                setResponse("Failed to fetch data: $e")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonObject = JSONObject(response.body!!.string())
                    setResponse(jsonObject.toString(3))
                } catch (e: JSONException) {
                    setResponse("Failed to parse data: $e")
                }
            }
        })
    }

    fun onRequestCodeClicked(view: View?) {
        val request = getAuthenticationRequest(AuthorizationResponse.Type.CODE)
        println("req" + request);
        AuthorizationClient.openLoginActivity(this, AUTH_CODE_REQUEST_CODE, request)
    }

    fun onRequestTokenClicked(view: View?) {
        val request = getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        println("req" + request);
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
//        println("Xx" + redirectUri.toString()); // save access token to device somewhere
        return AuthorizationRequest.Builder(CLIENT_ID, type, "spotistics://auth") //spotistics://auth") //"http://localhost/") //redirectUri.toString())
            .setShowDialog(false)
            .setScopes(arrayOf("user-top-read", "user-read-recently-played", "user-read-email", "user-read-private"))
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (response.error != null && !response.error.isEmpty()) {
            setResponse(response.error)
        }
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            mAccessToken = response.accessToken

            // write to res/values
            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString(getString(R.string.token), mAccessToken)
                apply()
            }

            println("maccesstoken " + mAccessToken)

        } else if (requestCode == AUTH_CODE_REQUEST_CODE) {
            mAccessCode = response.code
            println("req code" + response.state + response.expiresIn)

            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString(getString(R.string.token), mAccessCode)
                apply()
            }

            println("maccesscode " + mAccessCode)
        }
    }

    private fun setResponse(text: String) {
        runOnUiThread {
            val responseView = findViewById<TextView>(R.id.response_text_view)
            responseView.text = text
        }
    }

    private fun updateTokenView() {
        val tokenView = findViewById<TextView>(R.id.token_text_view)
        tokenView.text =
            getString(R.string.token, mAccessToken)
    }

    private fun updateCodeView() {
        val codeView = findViewById<TextView>(R.id.code_text_view)
        codeView.text =
            getString(R.string.code, mAccessCode)
    }

    private fun cancelCall() {
        if (mCall != null) {
            mCall!!.cancel()
        }
    }

    companion object {
        const val CLIENT_ID = "99c4249a81b040bfac0b3146288a64b7"
        const val AUTH_TOKEN_REQUEST_CODE = 0x10
        const val AUTH_CODE_REQUEST_CODE = 0x11
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(link: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(navController, link)
        }
        composable("home") {
            MainNavigation(navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(navController: NavHostController) {
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
                         Screens.History -> History(innerPadding, scrollState)
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