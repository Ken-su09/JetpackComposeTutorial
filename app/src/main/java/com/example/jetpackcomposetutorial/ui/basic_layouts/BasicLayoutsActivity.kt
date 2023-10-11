package com.example.jetpackcomposetutorial.ui.basic_layouts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.R
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class BasicLayoutsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                MySootheApp(windowSizeClass)
            }
        }
    }
}

//region ================================================================ SEARCH BAR ================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(value = "Search", onValueChange = {}, leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
    }, textStyle = TextStyle(color = Color(0xFF484644)), modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .heightIn(max = 56.dp)
    )
}

//endregion

//region =================================================================== CHARACTERS ==================================================================

@Composable
fun ListOfCharactersLayout(list: List<User>) {
    SectionTitle(text = "Users")
    Spacer(modifier = Modifier.height(16.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(items = list) { item ->
            CharacterLayout(item)
        }
    }
}

@Composable
fun CharacterLayout(item: User) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RoundedCornerShape(60)) {
            Image(
                painter = painterResource(item.icon),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(88.dp)
                    .width(88.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.name, color = Color.Black)
    }
}

//endregion

//region =========================================================== FAVORITE COLLECTIONS ===========================================================

@Composable
fun ListOfFavoriteCollections(list: List<Landscape>) {
    Spacer(modifier = Modifier.height(16.dp))
    SectionTitle(text = "Favorite Landscapes")
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(230.dp)
    ) {
        items(items = list) { item ->
            FavoriteCollection(item)
        }
    }
}

@Composable
fun FavoriteCollection(item: Landscape) {
    Surface(
        shape = MaterialTheme.shapes.medium, color = Color(0xFFe5e0dd), modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(item.icon), contentDescription = "",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = item.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

//endregion

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
           AppLandscape()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPortrait() {
    Scaffold(bottomBar = { HorizontalBottomNavigationView() }) { padding ->
        padding
        EntireLayout()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLandscape() {
    Row {
        VerticalBottomNavigationView()
        EntireLayout()
    }

}

@Composable
fun VerticalBottomNavigationView() {
    NavigationRail(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column {
            NavigationRailItem(icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") }, selected = true, onClick = { })
            NavigationRailItem(
                icon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Account") },
                selected = true,
                onClick = { })
        }
    }

}

@Composable
fun HorizontalBottomNavigationView() {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") }, selected = true, onClick = { })
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Account") },
            selected = true,
            onClick = { })
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text, style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun EntireLayout() {
    Column {
        SearchBar()
        ListOfCharactersLayout(listOfUsers())
        ListOfFavoriteCollections(listOfLandscape())
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark mode")
fun PreviewEntireLayout() {
//    val windowSizeClass = calculateWindowSizeClass(this@)
//    MySootheApp(windowSizeClass)
}


//region ============================================================== DATA AND LISTS ==============================================================

data class User(val name: String, val icon: Int)

private fun listOfUsers() = listOf(
    User("Superman", R.drawable.superman),
    User("Gojo", R.drawable.gojo),
    User("Gojo 2", R.drawable.gojo_2),
    User("Serpico", R.drawable.serpico),
    User("Guts", R.drawable.guts),
    User("Ikarugo", R.drawable.ikarugo),
    User("Dai", R.drawable.dai)
)

data class Landscape(val name: String, val icon: Int)

private fun listOfLandscape() = listOf(
    Landscape("Melancholic", R.drawable.melancholic),
    Landscape("Ender Lilies", R.drawable.lily),
    Landscape("Knight", R.drawable.knight),
    Landscape("Aristocrats", R.drawable.aristocrats),
    Landscape("Color", R.drawable.color),
)

//endregion