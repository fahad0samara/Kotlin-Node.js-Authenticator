package com.fahad.kotlinnodeauthenticator.ui.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


import com.fahad.kotlinnodeauthenticator.ui.screen.auth.login.LoginViewModel
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.register.RegisterScreen
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.register.RegistrationViewModel

fun NavGraphBuilder.authNavigation(
  navController: NavHostController,
  loginViewModel: LoginViewModel,
  registerViewModel: RegistrationViewModel,

  ) {

  navigation(
    startDestination = "login",
    route = "auth",

    )

  {

    composable("login") {
//      LoginScreen(
//        navController = navController, loginViewModel = loginViewModel
//      )
    }
    composable("register") {
      RegisterScreen(
        navController = navController, registrationViewModel = registerViewModel
      )
    }

  }
}




