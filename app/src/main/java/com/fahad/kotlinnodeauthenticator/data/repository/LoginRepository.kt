package com.fahad.kotlinnodeauthenticator.data.repository

import android.content.Context
import android.util.Log
import com.fahad.kotlinnodeauthenticator.data.remote.ApiConstants
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserAuthData
import com.fahad.kotlinnodeauthenticator.model.UserInfo

import io.ktor.client.HttpClient
import io.ktor.client.call.body

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject

class LoginRepository @Inject constructor(
  private val client: HttpClient,
  private val context: Context,
) {
  suspend fun loginUser(userData: UserAuthData): UserInfo {
    return try {
      val response: HttpResponse = withContext(Dispatchers.IO) {
        client.post("${ApiConstants.BASE_URL}login") {
          contentType(ContentType.Application.Json)
          setBody(userData)
        }
      }

      val responseBody = response.body<String>()

      Log.d("response", "Response: $responseBody")

      if (response.status.isSuccess()) {
        val dataJson = Json.decodeFromString<JsonObject>(responseBody)["data"]?.jsonObject
        val userJson = dataJson?.get("user")?.jsonObject
        val userId: Int? = userJson?.get("id")?.jsonPrimitive?.intOrNull
        val name: String? = userJson?.get("name")?.jsonPrimitive?.contentOrNull
        val email: String? = userJson?.get("email")?.jsonPrimitive?.contentOrNull

        UserInfo(success = true, id = userId, name = name, email = email)
      } else {
        UserInfo(success = false, errorMessage = "Error: ${response.status.value} - ${response.status.description}")
      }
    } catch (e: Exception) {
      UserInfo(success = false, errorMessage = "Error: ${e.message}")
    }
  }


}