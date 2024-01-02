package com.fahad.kotlinnodeauthenticator.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginData(
  val email: String,
  val password: String
)
