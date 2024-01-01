package com.fahad.kotlinnodeauthenticator.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
  val id: Int,
  val name: String,
  val email: String,
  val password: String,
  val profile_image: String,
  val role: String = "user"
)
