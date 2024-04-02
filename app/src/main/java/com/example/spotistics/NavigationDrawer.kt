package com.example.spotistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotistics.ui.theme.quicksandFamily

data class NavigationItem (
    val id: String,
    val title: String,
    val icon: ImageVector,
    val contentDescription: String
)

@Composable
fun NavigationDrawer(
    onItemClick: (NavigationItem) -> Unit
) {
    val navItems = listOf(
        NavigationItem(
            id = "home",
            title = "Home",
            contentDescription = "",
            icon = Icons.Default.Home
        ),
        NavigationItem(
            id = "search",
            title = "Search",
            contentDescription = "",
            icon = Icons.Default.Search
        ),
        NavigationItem(
            id = "recs",
            title = "Recommendations",
            contentDescription = "",
            icon = Icons.Default.FormatListNumbered
        ),
        NavigationItem(
            id = "throwbacks",
            title = "Throwbacks",
            contentDescription = "",
            icon = Icons.Default.Celebration
        ),
        NavigationItem(
            id = "stats",
            title = "Statistics",
            contentDescription = "",
            icon = Icons.Default.QueryStats
        ),
        NavigationItem(
            id = "history",
            title = "History",
            contentDescription = "",
            icon = Icons.Default.SettingsBackupRestore
        ),
        NavigationItem(
            id = "settings",
            title = "Settings",
            contentDescription = "",
            icon = Icons.Default.Settings
        ),
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        LazyColumn(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.spotify_logo),
                            contentDescription = null,
                            modifier = Modifier.size(45.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Mr. Goose",
                            color = Color.Black,
                            fontSize = 40.sp,
                            fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            items(navItems) { item ->
                Row(
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
                        }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
