package com.fahad.kotlinnodeauthenticator.model


import kotlinx.serialization.Serializable

@Serializable
data class UserAuthData(
  val id: Int = 0,
  val name: String = "",
  val email: String,
  val password: String,
  val role: String = "user"
)

