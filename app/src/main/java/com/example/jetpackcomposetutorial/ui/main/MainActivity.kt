package com.example.jetpackcomposetutorial.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.R
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyAppIntroduction()
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: MessageViewState) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        var isSelected by rememberSaveable { mutableStateOf(false) }

        Image(
            painter = painterResource(if (isSelected) R.drawable.ic_check else message.authorIcon),
            contentDescription = "Contact Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(width = 1.5.dp, color = MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            if (isExpanded) 48.dp else 0.dp, label = ""
        )
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface, label = ""
        )

        Column(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { isExpanded = !isExpanded }, onLongPress = { isSelected = !isSelected })
        }) {
            Row {
                Text(
                    text = message.authorName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 1.dp,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.padding(extraPadding)
            ) {
                Text(
                    text = message.content,
                    modifier = Modifier.padding(all = 8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<MessageViewState>) {
    LazyColumn {
        items(items = messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun HomeScreen(onContinueClicked : () -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .border(1.dp, Color.Black)
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the Basics Codelab!", color = Color(0xFF434546)
        )
        Button(modifier = Modifier.padding(20.dp), onClick = onContinueClicked) {
            Text(text = "Continue", color = Color.White)
        }
    }
}

@Composable
fun MyAppIntroduction() {
    var shouldSnowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFFBFC7CD)
    ) {
        if (shouldSnowOnBoarding) {
            HomeScreen(onContinueClicked = { shouldSnowOnBoarding = false })
        } else {
            Conversation(fakeListOfMessages())
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "light mode")
@Composable
fun PreviewConversation() {
    JetpackComposeTutorialTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            MyAppIntroduction()
        }
    }
}

private fun fakeListOfMessages() = listOf(
    MessageViewState(1L, "Ken S", R.drawable.knight, "Test 123"),
    MessageViewState(2L, "Ken S", R.drawable.knight, "Ryuketsu no heiwa"),
    MessageViewState(3L, "Ken S", R.drawable.knight, "Titre : Arc Créature Incompris"),
    MessageViewState(
        4L,
        "Ken S",
        R.drawable.knight,
        "La Créature incompris est une bête sauvage mais de nature défensive, elle cherchait juste à venger les villageois qui avaient tué ses enfants. Kuruda décide alors de ne pas tuer la créature, elle propose à la créature un marché, il lui ramènerait ses enfants en échange, elle devrait chercher une nouvelle habitation. Marché qui a énormément déplu aux villageois qui souhaitaient la mort de la Créature.. On pourrait voir une scène ou, alors qu'ils transportent les corps sans vie des enfants de la Créature, les villageois lui jettent des pierres en signe de protestation. Car sans preuve de la mort de la créature, ils vivront dans la peur."
    ),
    MessageViewState(4L, "TEST TEST", R.drawable.snap_test, "Titre : Arc 5"),
)