package com.example.jetpackcomposetutorial.ui.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.R
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CardView(Topic("Superman", R.drawable.superman))
                }
            }
        }
    }
}

data class Topic(val name: String, val image: Int)

@Composable()
fun CardView(topic: Topic) {
    var isSelected by remember { mutableStateOf(false) }
    Card(shape = RoundedCornerShape(topStart = if (isSelected) 20.dp else 0.dp), modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(onTap = { isSelected = !isSelected })
    }) {
        Row {
            Image(
                painter = painterResource(topic.image),
                contentDescription = "Contact Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .border(width = 1.5.dp, color = MaterialTheme.colorScheme.secondary)
            )
            Text(
                text = topic.name,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview()
@Composable()
fun PreviewCardView() {
    JetpackComposeTutorialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CardView(Topic("Superman", R.drawable.superman))
        }
    }
}