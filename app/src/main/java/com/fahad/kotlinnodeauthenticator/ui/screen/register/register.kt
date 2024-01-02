package com.fahad.kotlinnodeauthenticator.ui.screen.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.kotlinnodeauthenticator.data.remote.ApiConstants
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserData
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegistrationViewModel1 : ViewModel() {

  private val _registrationResult = MutableStateFlow<OperationResult?>(null)
  val registrationResult: StateFlow<OperationResult?> = _registrationResult


  // Create HttpClient with ContentNegotiation
  private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
      json()
    }
  }
  fun registerUser(userRegistrationData: UserData) {
    viewModelScope.launch {
      try {

        // Make POST request
        val response: HttpResponse = withContext(Dispatchers.IO) {
          client.post("${ApiConstants.BASE_URL}register") {
            contentType(ContentType.Application.Json)
            setBody(userRegistrationData)
          }
        }

        if (response.status.isSuccess()) {
          _registrationResult.value = OperationResult(success = true, errorMessage = null)
          Log.i("RegistrationResult", "Success: ${response.status.value} - ${response.status.description}")
        } else {
          _registrationResult.value =
            OperationResult(success = false, errorMessage = "Error: ${response.status.value} - ${response.status.description}")
          Log.e(
            "RegistrationResult",
            "Error: ${response.status.value} - ${response.status.description}"
          )
        }
      } catch (e: Exception) {
        _registrationResult.value = OperationResult(success = false, errorMessage = "Error: ${e.message}")
        Log.e("RegistrationResult", "Network error: ${e.message}", e)
      } finally {
        // Close the HttpClient in the finally block
        client.close()
      }
    }
  }
}

