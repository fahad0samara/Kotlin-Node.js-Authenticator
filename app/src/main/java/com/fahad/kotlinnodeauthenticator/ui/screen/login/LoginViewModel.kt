package com.fahad.kotlinnodeauthenticator.ui.screen.login

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fahad.kotlinnodeauthenticator.data.repository.LoginRepository
import com.fahad.kotlinnodeauthenticator.model.OperationResult

import com.fahad.kotlinnodeauthenticator.model.UserData
import com.fahad.kotlinnodeauthenticator.model.UserLoginData
import dagger.hilt.android.lifecycle.HiltViewModel
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

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: LoginRepository
) : ViewModel() {

  private val _loginResult = MutableStateFlow<OperationResult?>(null)
  val loginResult: StateFlow<OperationResult?> = _loginResult



  fun loginUser(userLoginData: UserLoginData) {
    viewModelScope.launch {
      try {
        val result = repository.loginUser(userLoginData)

        if (result.success) {
          _loginResult.value = OperationResult(success = true, errorMessage = null)
          Log.i("LoginResult", "Success")
        } else {
          _loginResult.value =
            OperationResult(success = false, errorMessage = result.errorMessage)
          Log.e("LoginResult", "Error: ${result.errorMessage}")
        }
      } catch (e: Exception) {
        _loginResult.value =
          OperationResult(success = false, errorMessage = "Error: ${e.message}")
        Log.e("LoginResult", "Network error: ${e.message}", e)
      }
    }
  }
}
