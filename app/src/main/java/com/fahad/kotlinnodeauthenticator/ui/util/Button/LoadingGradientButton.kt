package com.fahad.kotlinnodeauthenticator.ui.util.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Text


import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingGradientButton(
  modifier: Modifier = Modifier,
  text: String,
  isLoading: Boolean,
  onClick: () -> Unit,
  enabled: Boolean = true, // Default to true
  textLoading: String
) {
  Button(
    onClick = onClick,
    enabled = enabled, // Set the enabled state based on the provided boolean
    modifier = modifier
      .height(48.dp)
      .clip(shape = RoundedCornerShape(40.dp))
      .padding(16.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,
      contentColor = Color.Transparent,
    ),
    contentPadding = PaddingValues(0.dp),
  ) {
    if (isLoading) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            brush = Brush.horizontalGradient(
              colors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3)),
              startX = 0f,
              endX = 100f
            )
          )
          .clip(shape = RoundedCornerShape(40.dp))
          .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator(
          modifier = Modifier.size(24.dp),
          color = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = textLoading, color = Color.White)
      }
    } else {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            brush = Brush.horizontalGradient(
              colors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3)),
              startX = 0f,
              endX = 100f
            )
          )
          .clip(shape = RoundedCornerShape(40.dp))
          .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = text,
          style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )
      }
    }
  }
}
