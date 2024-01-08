package com.fahad.kotlinnodeauthenticator.data.repository


import android.util.Log
import com.fahad.kotlinnodeauthenticator.data.remote.ApiConstants
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserAuthData
import com.fahad.kotlinnodeauthenticator.ui.util.valid.validateEmailAndPassword
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val client: HttpClient) {
  suspend fun registerUser(userData: UserAuthData): OperationResult {
    val validation = validateEmailAndPassword(userData.email, userData.password)

    if (!validation.success) {
      // Return the validation result if it fails
      return validation
    }

    return try {
      // Make the actual request
      val response: HttpResponse = withContext(Dispatchers.IO) {
        client.post("${ApiConstants.BASE_URL}register") {
          contentType(ContentType.Application.Json)
          setBody(userData)
        }
      }

      // Log response
      Log.d("RegistrationRepository", "Response: ${response.body<String>()}")

      if (response.status.isSuccess()) {
        // Deserialize the response body to UserAuthData
        val userProfile: UserAuthData? = response.body<UserAuthData?>()

        // Extract user ID from the response body
        val userId: Int? = userProfile?.id

        OperationResult(success = true, errorMessage = null, id = userId)
      } else {
        OperationResult(success = false, errorMessage = "Error: ${response.status.value} - ${response.status.description}")
      }

    } catch (e: Exception) {
      OperationResult(success = false, errorMessage = "Error: ${e.message}",)
    } finally {
      client.close()
    }
  }
}











