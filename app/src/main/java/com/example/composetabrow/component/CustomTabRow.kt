package com.example.composetabrow.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetabrow.base.color

@Composable
fun CustomTabRow(data: List<String>, click: (index: Int, data: String) -> Unit) {
    var tabIndex by remember { mutableStateOf(0) }

    androidx.compose.material.TabRow(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(30.dp)),
        backgroundColor = "#8a11ed".color,
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                color = Color.White
            )
        }
    ) {
        data.forEachIndexed { index, data ->
            Tab(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                selected = tabIndex == index,
                onClick = {
                    tabIndex = index
                    click(index, data)
                },
                text = {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .alpha(if (tabIndex == index) 1f else 0.5f),
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
    CustomTabRow(listOf("Sosyal", "Keşfet", "Sen")) { i, d -> }
}

