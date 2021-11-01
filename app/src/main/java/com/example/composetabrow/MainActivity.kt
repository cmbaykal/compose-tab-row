package com.example.composetabrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetabrow.component.CustomTabRow
import com.example.composetabrow.ui.theme.ComposeTabRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityUI()
        }
    }
}

@Composable
fun MainActivityUI() {
    ComposeTabRowTheme {
        Surface(color = MaterialTheme.colors.background) {
            CustomTabRow(listOf("Sosyal", "Keşfet", "Sen")) { i, d -> }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTabRowTheme {
        MainActivityUI()
    }
}