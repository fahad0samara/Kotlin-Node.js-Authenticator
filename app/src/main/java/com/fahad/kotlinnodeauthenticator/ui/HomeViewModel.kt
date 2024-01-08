package com.fahad.kotlinnodeauthenticator.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fahad.kotlinnodeauthenticator.data.repository.LoginRepository
import com.fahad.kotlinnodeauthenticator.model.Response
import com.fahad.kotlinnodeauthenticator.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: LoginRepository
) : ViewModel() {
//
//  private val _userResult = MutableStateFlow<Response<UserInfo>>(Response.Loading())
//  val userResult: StateFlow<Response<UserInfo>> = _userResult
//
//  fun setUserOperationResult(result: UserInfo) {
//    Log.d("HomeViewModel", "Setting user result: $result")
//    _userResult.value = Response.Success(result)
//    Log.d("HomeViewModel", "User result: ${_userResult.value}")
//    Log.d("HomeViewModel", "User result: ${userResult.value}")
//    Log.d("HomeViewModel", "User result: ${Response.Success(result)}")
//  }



  fun logout() {
    // Clear the UserInfo
//    _userResult.value = Response.Loading()
  }
}







