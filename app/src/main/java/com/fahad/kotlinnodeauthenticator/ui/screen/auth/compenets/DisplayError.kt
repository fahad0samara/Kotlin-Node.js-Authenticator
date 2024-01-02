package com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.ui.theme.dimens

@Composable

fun DisplayError(result: OperationResult) {
  if (!result.success) {
    Text(
      text = result.errorMessage ?: "Unknown error",
      color = Color.Red,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimens.small1)
    )
  }
}
