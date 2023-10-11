package com.example.jetpackcomposetutorial.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var isHiding by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .border(1.dp, Color.Black)
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the Basics Codelab!",
            color = Color(0xFF434546)
        )
        Button(modifier = Modifier.padding(20.dp),
            onClick = { isHiding = !isHiding }) {
            Text(text = "Continue", color = Color.White)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark mode")
@Composable
fun GreetingPreview() {
    JetpackComposeTutorialTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFBFC7CD)
        ) {
        }
    }
}