package com.fahad.kotlinnodeauthenticator.data.remote
import android.content.Context
import android.util.Log
import com.fahad.kotlinnodeauthenticator.data.remote.ApiConstants
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserAuthData

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
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

class LoginRepository1 @Inject constructor(
  private val client: HttpClient,
  private val context: Context,
) {

  suspend fun loginUser(userData: UserAuthData): OperationResult {
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



        val userJson = dataJson?.get("data")?.jsonObject
        val userId: Int? = userJson?.get("id")?.jsonPrimitive?.intOrNull
        val name: String? = userJson?.get("name")?.jsonPrimitive?.contentOrNull
        val email: String? = userJson?.get("email")?.jsonPrimitive?.contentOrNull

        OperationResult(success = true, errorMessage = null, id = userId, name = name, email = email)
      } else {
        OperationResult(success = false, errorMessage = "Error: ${response.status.value} - ${response.status.description}")
      }
    } catch (e: Exception) {
      OperationResult(success = false, errorMessage = "Error: ${e.message}")
    }
  }

  private fun saveToken(token: String?) {
    // Save the token to local storage (SharedPreferences, secure storage, etc.)
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("token", token).apply()
  }

  fun getToken(): String? {
    // Retrieve the token from local storage
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    return sharedPreferences.getString("token", null)
  }

  suspend fun fetchUserInfo(): OperationResult {
    return try {
      // Fetch user information using the stored token
      val token = getToken()
      if (token != null) {
        val response: HttpResponse = withContext(Dispatchers.IO) {
          client.get("${ApiConstants.BASE_URL}getUserInfo") {
            header("Authorization", "Bearer $token")
          }
        }

        val responseBody = response.body<String>()
        Log.d("responseBody", "Response: $responseBody")
        Log.d("responseBody", "Response: $response")
        if (response.status.isSuccess()) {
          val userJson = Json.decodeFromString<JsonObject>(responseBody)["data"]?.jsonObject
          val name: String? = userJson?.get("name")?.jsonPrimitive?.contentOrNull
          val email: String? = userJson?.get("email")?.jsonPrimitive?.contentOrNull

          OperationResult(success = true, errorMessage = null, name = name, email = email)

        } else {
          // Handle error if needed
          OperationResult(success = false, errorMessage = "Failed to fetch user information")
        }
      } else {
        // Handle the case where the token is null
        OperationResult(success = false, errorMessage = "Token is null")
      }
    } catch (e: Exception) {
      // Handle other exceptions
      OperationResult(success = false, errorMessage = "Error: ${e.message}")
    }
  }


  fun clearToken() {
    // Clear the stored token from local storage
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    sharedPreferences.edit().remove("token").apply()
  }
}
