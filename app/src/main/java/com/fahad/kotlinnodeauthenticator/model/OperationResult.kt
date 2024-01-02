package com.fahad.kotlinnodeauthenticator.model

data class OperationResult(
  val success: Boolean,
  val errorMessage: String? = null,
  val isLoading: Boolean = false
)

