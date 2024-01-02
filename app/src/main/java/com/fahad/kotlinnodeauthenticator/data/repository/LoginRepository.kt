package com.fahad.kotlinnodeauthenticator.data.repository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.kotlinnodeauthenticator.data.remote.ApiConstants
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserData
import com.fahad.kotlinnodeauthenticator.model.UserLoginData
import com.fahad.kotlinnodeauthenticator.ui.util.valid.validateEmailAndPassword
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
import javax.inject.Inject

class LoginRepository @Inject constructor (private val client: HttpClient) {

  suspend fun loginUser(userData: UserLoginData): OperationResult {
    val validation = validateEmailAndPassword(userData.email, userData.password)

    if (!validation.success) {
      // Return the validation result if it fails
      return validation
    }

    return try {
      val response: HttpResponse = withContext(Dispatchers.IO) {
        client.post("${ApiConstants.BASE_URL}login") {
          contentType(ContentType.Application.Json)
          setBody(userData)
        }
      }

      if (response.status.isSuccess()) {
        OperationResult(success = true, errorMessage = null)
      } else {
        OperationResult(success = false, errorMessage = "Error: ${response.status.value} - ${response.status.description}")
      }
    } catch (e: Exception) {
      OperationResult(success = false, errorMessage = "Error: ${e.message}")
    }
  }
}
