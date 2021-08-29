package com.example.composelearning.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.composelearning.ui.theme.ComposeLearningTheme

class ComposeBasicsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                myApp {
                    myScreenContent()
                }
            }
        }
    }
}

@Composable
fun myApp(content : @Composable () -> Unit){
    Surface(color = MaterialTheme.colors.primary) {
        content()
    }
}

@Composable
fun displayText(text : String){

    var isSelected by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(targetValue = if (isSelected) Color.Red else Color.Green)

    Text(
        text = "$text",
        modifier = Modifier.padding(10.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

@Composable
fun myScreenContent(names : List<String> = List(1000) {  "Android there"}){

    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Divider(thickness = 5.dp)
        Counter(
            count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount}
        )
    }
}
@Composable
fun NameList(names : List<String>, modifier: Modifier = Modifier){

    LazyColumn(modifier = modifier){
        items(names){ name ->
            displayText(text = name)
            Divider(thickness = 5.dp)
        }
    }
}

@Composable
fun Counter(count : Int, updateCount : (Int) -> Unit){
    Button(onClick = { updateCount(count + 1)},
    colors = ButtonDefaults.buttonColors(
        backgroundColor = if (count > 5) Color.Green else Color.Black
    )) {
        Text(text = "Button click $count times")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLearningTheme {
        myApp {
            Column {
                myScreenContent()
            }
        }
    }
}