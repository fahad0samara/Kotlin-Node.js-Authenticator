package com.fahad.kotlinnodeauthenticator.model

data class OperationResult(
  val success: Boolean,
  val errorMessage: String? = null,
  val isLoading: Boolean = false,
  val id: Int? = null,
  val name: String? = null,
  val email: String? = null
)

