
package com.fahad.kotlinnodeauthenticator.ui.screen.auth.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.DisplayError
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.EmailAndPasswordInputs
import com.fahad.kotlinnodeauthenticator.ui.screen.auth.compenets.NavigationText
import androidx.compose.runtime.remember
import com.fahad.kotlinnodeauthenticator.model.UserAuthData

import com.fahad.kotlinnodeauthenticator.ui.screen.auth.login.LoginViewModel
import com.fahad.kotlinnodeauthenticator.ui.theme.dimens
import com.fahad.kotlinnodeauthenticator.ui.util.Button.LoadingButton

@SuppressLint("SuspiciousIndentation")
@Composable
fun RegisterScreen(
    registrationViewModel: RegistrationViewModel,

  navController: androidx.navigation.NavController

) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  val registrationResult by registrationViewModel.registrationResult.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimens.medium1),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Registration Page",
                color = MaterialTheme.colorScheme.primary,
                fontSize =MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = dimens.medium1)
            )

            Text(
                text = "Please fill in the details below to create an account",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = dimens.medium1)
            )





            EmailAndPasswordInputs(
                name = name,
                onNameChange = { name = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                isError = registrationResult?.success == false,
                showNameField = true
            )

            // Spacer to create some space between input fields and button
            Spacer(modifier = Modifier.height(dimens.small3))

            // Display error message if registrationResult is a failure
          registrationResult?.let { DisplayError(it) }

            // Register Button
          LoadingButton(
            text = "Register",
            isLoading = registrationResult?.isLoading ?: false,
            enabled = !(name.isBlank() || email.isBlank() || password.isBlank()),
            textloading = "Registering...",
            onClick = {
              val userRegistrationData = UserAuthData(
                id = 0,

                name = name,
                email = email,
                password = password,
                role = "user",
              )
              registrationViewModel.registerUser(userRegistrationData, navController)
            }
            )


            NavigationText(
                text = "Already have an account? Login",
                onClick = {
                    navController.navigate("login")
                }
            )
        }
    }
}


















