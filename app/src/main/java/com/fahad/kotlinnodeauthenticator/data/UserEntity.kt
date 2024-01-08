package com.fahad.kotlinnodeauthenticator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val name: String, // Include name property
  val email: String,
  val password: String
)
