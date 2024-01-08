package com.fahad.kotlinnodeauthenticator.ui


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fahad.kotlinnodeauthenticator.model.Response
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.login.LoginViewModel
import kotlinx.coroutines.delay

//@Composable
//fun HomeScreen( navController: NavController,loginViewModel: LoginViewModel ) {
//
//  val userResult by loginViewModel.userResult.collectAsState()
//  Log.d("HomeScreen", "User result: $userResult")
//
//  when (userResult) {
//    is Response.Success -> {
//      val userInfo = (userResult as Response.Success).data
//      Column(
//        modifier = Modifier
//          .fillMaxSize()
//          .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//      ) {
//        Text("User ID: ${userInfo.id}")
//        Text("User Name: ${userInfo.name}")
//        Text("User Email: ${userInfo.email}")
//        // Add other UI components as needed
//        Button(
//          onClick = {
//            // Perform logout action
//
//            navController.navigate("login")
//          },
//          modifier = Modifier
//            .padding(top = 16.dp)
//        ) {
//          Text("Logout")
//        }
//      }
//    }
//    is Response.Loading -> {
//      // Show loading state if needed
//      Text("Loading...")
//    }
//    is Response.Failure -> {
//      // Handle failure state if needed
//      val errorMessage = (userResult as Response.Failure).exception.message
//      Text("Error: $errorMessage")
//    }
//    else -> {
//      // Handle other states if needed
//    }
//  }
//}
//
















