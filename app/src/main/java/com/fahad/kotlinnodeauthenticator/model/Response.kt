package com.fahad.kotlinnodeauthenticator.model

sealed class Response<out T> {
  data class Loading<T>(val data: T? = null) : Response<T>()
  data class Success<out T>(val data: T) : Response<T>()
  data class Failure(val exception: Exception) : Response<Nothing>()
}