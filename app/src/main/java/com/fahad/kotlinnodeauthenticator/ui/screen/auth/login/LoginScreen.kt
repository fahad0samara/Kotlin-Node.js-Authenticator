//package com.fahad.kotlinnodeauthenticator.ui.screen.auth.login
//
//
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//
//import androidx.compose.material3.MaterialTheme
//
//import androidx.compose.material3.Text
//
//import androidx.compose.runtime.Composable
//
//
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.fahad.kotlinnodeauthenticator.model.Response
//import com.fahad.kotlinnodeauthenticator.model.UserAuthData
//
//import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.DisplayError
//
//import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.EmailAndPasswordInputs
//
//import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.NavigationText
//import com.fahad.kotlinnodeauthenticator.ui.theme.dimens
//import com.fahad.kotlinnodeauthenticator.ui.util.Button.LoadingButton
//
//@SuppressLint("SuspiciousIndentation")
//@Composable
//
//fun LoginScreen(
//  loginViewModel: LoginViewModel,
//  navController: NavController
//) {
//  var email by rememberSaveable { mutableStateOf("") }
//  var password by rememberSaveable { mutableStateOf("") }
//
//  val loginResult by loginViewModel.loginResult.collectAsState()
//
//  Box(
//    modifier = Modifier
//      .fillMaxSize()
//      .padding(dimens.medium1),
//    contentAlignment = Alignment.Center
//  ) {
//    Column(
//      modifier = Modifier.fillMaxWidth(),
//      horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//      Text(
//        text = "Welcome to the App",
//        color = MaterialTheme.colorScheme.primary,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
//      )
//
//      Text(
//        text = "Please log in to continue",
//        color = MaterialTheme.colorScheme.onBackground,
//        fontSize = 16.sp,
//        textAlign = TextAlign.Center,
//        modifier = Modifier.padding(top = 8.dp)
//      )
//
//      Spacer(modifier = Modifier.height(50.dp))
//
//      EmailAndPasswordInputs(
//        email = email,
//        onEmailChange = { email = it },
//        password = password,
//        onPasswordChange = { password = it },
//        isError = loginResult is Response.Failure,
//        showNameField = false,
//        name = "",
//        onNameChange = { },
//      )
//
//
//
//
//      // Login Button
//      LoadingButton(
//        text = "Login",
//        isLoading = loginResult is Response.Loading,
//        enabled = !(email.isBlank() || password.isBlank()),
//        textloading = "Login...",
//        modifier = Modifier
//          .fillMaxWidth()
//          .padding(top = dimens.medium1),
//        onClick = {
//          val userCommonData = UserAuthData(
//            email = email,
//            password = password
//          )
//          loginViewModel.loginUser(userCommonData, navController)
//        },
//      )
//
//      // Navigation button to registration screen
//      NavigationText(
//        text = "Don't have an account? Register",
//        onClick = {
//          navController.navigate("register")
//        }
//      )
//    }
//  }
//}
//
//
//
//
//
//
//
//
//
//
//
