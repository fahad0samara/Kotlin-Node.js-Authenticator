package com.fahad.kotlinnodeauthenticator.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import com.fahad.kotlinnodeauthenticator.ui.navigation.auth.authNavigation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fahad.kotlinnodeauthenticator.R
import com.fahad.kotlinnodeauthenticator.model.Response

import com.fahad.kotlinnodeauthenticator.ui.HomeViewModel
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.login.LoginViewModel
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.register.RegistrationViewModel
import kotlinx.coroutines.delay
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RootNavigation(navController: NavHostController) {
  val loginViewModel: LoginViewModel = hiltViewModel()
  val registerViewModel: RegistrationViewModel = hiltViewModel()
  val homeViewModel: HomeViewModel = hiltViewModel()





  // LaunchedEffect to navigate based on login status



  // In RootNavigation composable
  NavHost(
    navController = navController,
    route = "root",
    startDestination = "splash"
  ) {
    composable(route = "splash") {
      SplashScreen( navController = navController)
    }
    authNavigation(
      navController = navController,
      loginViewModel = loginViewModel,
      registerViewModel = registerViewModel
    )


  }
}







  @Composable
  fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
      // Simulate a splash screen delay if needed
      delay(2000) // 2 seconds delay, adjust as needed

      // Navigate to the login screen
      navController.navigate("login")
    }


    Box(
      modifier = Modifier.fillMaxSize(),

      contentAlignment = Alignment.Center
    ) {
      // Add your splash screen content
      Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
      ) {

        Image(
          painter = painterResource(id = R.drawable.logo),

          contentDescription = null,
          alignment = Alignment.Center,
          modifier = Modifier.height(300.dp)

        )

      }
    }

  }





