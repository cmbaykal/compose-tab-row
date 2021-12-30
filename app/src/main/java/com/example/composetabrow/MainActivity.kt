package com.example.composetabrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetabrow.base.color
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

enum class TabRowNavigation(private val title: String) {
    Social("Sosyal"),
    Explore("Keşfet"),
    You("Sen");

    override fun toString() = title
}

@Composable
fun MainActivityUI() {
    var tabIndex by remember { mutableStateOf(1) }

    ComposeTabRowTheme {
        Column(Modifier.background(MaterialTheme.colors.background)) {
            CustomTabRow(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                data = TabRowNavigation.values()
            ) { _, _ ->
            }

            CustomTabRow(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 15.dp,
                            topEnd = 15.dp
                        )
                    ),
                backgroundColor = "#324178".color,
                contentColor = "#ffff51".color,
                indicator = {
                    TabRowDefaults.Indicator(
                        Modifier.customIndicatorOffset(it[tabIndex]),
                        color = "#ffff51".color
                    )
                },
                selectedTabIndex = tabIndex,
                data = TabRowNavigation.values()
            ) { index, data ->
                tabIndex = index
            }
        }

    }
}

fun Modifier.customIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val size = 10.dp
    val tabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + tabWidth / 2 - size / 2,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .padding(bottom = 10.dp)
        .offset(x = indicatorOffset)
        .width(size)
        .height(size)
        .clip(RoundedCornerShape(2.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTabRowTheme {
        MainActivityUI()
    }
}