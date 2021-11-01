package com.example.composetabrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetabrow.TabRow
import com.example.composetabrow.ui.theme.ComposeTabRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTabRowTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityUI()
                }
            }
        }
    }
}

@Composable
fun MainActivityUI() {
    TabRow()
}

@Composable
fun TabRow() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "Sosyal",
        "Keşfet",
        "Sen"
    )
    TabRow(
        modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(30.dp)),
        backgroundColor = Color.DarkGray,
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                color = Color.White
            )
        }
    ) {
        tabData.forEachIndexed { index, data ->
            Tab(modifier = Modifier.height(60.dp).fillMaxWidth(), selected = tabIndex == index, onClick = {
                tabIndex = index
            }, text = {
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = data,
                    color = Color.White,
                    fontSize = 14.sp
                )
            })
        }
    }

}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val size = 6.dp
    val tabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + tabWidth / 2 - size / 2,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .padding(bottom = 15.dp)
        .offset(x = indicatorOffset)
        .width(size)
        .height(size)
        .clip(RoundedCornerShape(6.dp))

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTabRowTheme {
        MainActivityUI()
    }
}