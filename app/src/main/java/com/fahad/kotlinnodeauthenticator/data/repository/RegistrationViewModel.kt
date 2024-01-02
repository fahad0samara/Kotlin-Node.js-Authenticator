package com.fahad.kotlinnodeauthenticator.data.repository

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fahad.kotlinnodeauthenticator.model.OperationResult

import com.fahad.kotlinnodeauthenticator.model.UserData
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
class RegistrationViewModel @Inject constructor(
    private val repository: RegistrationRepository
) : ViewModel(){
//  private val repository: RegistrationRepository = RegistrationRepository()

  private val _registrationResult = MutableStateFlow<OperationResult?>(null)
  val registrationResult: StateFlow<OperationResult?> = _registrationResult

  fun registerUser(userData:UserData){
    viewModelScope.launch {
      val result = repository.registerUser(userData)
        _registrationResult.value = result
    }

  }
}