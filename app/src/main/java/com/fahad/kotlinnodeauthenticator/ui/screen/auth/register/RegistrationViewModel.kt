package com.fahad.kotlinnodeauthenticator.ui.screen.auth.register


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationRepository
import com.fahad.kotlinnodeauthenticator.model.OperationResult
import com.fahad.kotlinnodeauthenticator.model.UserAuthData

import com.fahad.kotlinnodeauthenticator.ui.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class RegistrationViewModel @Inject constructor(
  private val repository: RegistrationRepository,



  ) : ViewModel() {

  private val _registrationResult = MutableStateFlow<OperationResult?>(null)
  val registrationResult: StateFlow<OperationResult?> = _registrationResult





  fun registerUser(userCommonData: UserAuthData, navController: NavController) {
    viewModelScope.launch {
      try {
        val result = repository.registerUser(userCommonData)

        if (result.success) {
            _registrationResult.value = result












          navController.navigate("home")
        } else {
          _registrationResult.value = OperationResult(success = false, errorMessage = result.errorMessage)
        }



        navController.navigate("home")
      } catch (e: Exception) {
        _registrationResult.value = OperationResult(success = false, errorMessage = "Error: ${e.message}")
        Log.e("RegistrationResult", "Error: ${e.message}", e)
      }
    }
  }
}










