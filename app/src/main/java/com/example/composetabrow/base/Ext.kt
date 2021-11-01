package com.example.composetabrow.base

import androidx.compose.ui.graphics.Color

inline val String.color get() = Color(android.graphics.Color.parseColor(this))
