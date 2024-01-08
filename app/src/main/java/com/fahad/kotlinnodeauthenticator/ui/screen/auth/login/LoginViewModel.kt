package com.fahad.kotlinnodeauthenticator.ui.screen.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.navigation.NavController
import com.fahad.kotlinnodeauthenticator.data.repository.LoginRepository
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserAuthData

import com.fahad.kotlinnodeauthenticator.ui.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: LoginRepository,
  private val homeViewModel: HomeViewModel
) : ViewModel() {

  private val _loginResult = MutableStateFlow<OperationResult?>(null)
  val loginResult: StateFlow<OperationResult?> = _loginResult

  init {
    // Check for an existing token when the ViewModel is created
    val token = repository.getToken()
    if (token != null) {
      // For simplicity, we assume the token is always valid in this example
      val result = OperationResult(success = true, errorMessage = null)
      _loginResult.value = result

      // Fetch user information immediately after login
      fetchUserInfoAfterLogin(result)
    }
  }

  fun loginUser(userCommonData: UserAuthData, navController: NavController) {
    viewModelScope.launch {
      try {
        val result = repository.loginUser(userCommonData)

        if (result.success) {
          _loginResult.value = result
          homeViewModel.setUserInfo(result)

          navController.navigate("home")
        } else {
          _loginResult.value =
            OperationResult(success = false, errorMessage = result.errorMessage)
        }
      } catch (e: Exception) {
        _loginResult.value =
          OperationResult(success = false, errorMessage = "Error: ${e.message}")
      }
    }
  }

  private fun fetchUserInfoAfterLogin(result: OperationResult) {
    viewModelScope.launch {
      try {
        val userInfo = repository.fetchUserInfo()
        homeViewModel.setUserInfo(
          OperationResult(
            success = true,
            errorMessage = null,
            name = userInfo.name,
            email = userInfo.email
          )
        )
        Log.d("LoginViewModel", "Fetched user info after login: $userInfo")
      } catch (e: Exception) {
        // Handle errors if needed
        Log.e("LoginViewModel", "Error fetching user info after login: ${e.message}")
      }
    }
  }
}












