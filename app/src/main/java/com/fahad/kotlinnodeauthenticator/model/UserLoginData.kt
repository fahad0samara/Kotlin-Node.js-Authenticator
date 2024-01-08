package com.fahad.kotlinnodeauthenticator.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
  val id: Int?,
  val name: String?,
  val email: String?
)
