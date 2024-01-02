
package com.fahad.kotlinnodeauthenticator.ui.screen.auth.register

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationViewModel
import com.fahad.kotlinnodeauthenticator.model.UserData
import com.fahad.kotlinnodeauthenticator.ui.theme.dimens
import com.fahad.kotlinnodeauthenticator.ui.util.Button.LoadingButton

@Composable
fun RegisterScreen(
  registrationViewModel: RegistrationViewModel = viewModel()

) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  val registrationResult by registrationViewModel.registrationResult.collectAsState()



//    // Get the result of the image picker
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        photoUri = uri
//    }

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

            // Center the image and show an icon if no image is selected
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                // Display the selected image or show an icon
//                if (photoUri != null) {
//                    AsyncImageProfile(photoUrl = photoUri.toString())
//                } else {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = "No photo selected",
//                        tint = MaterialTheme.colorScheme.primary,
//                        modifier = Modifier
//                            .size( dimens.imageSize0)
//                            .align(Alignment.Center)
//                            .border(2.dp, Color.Yellow, CircleShape)
//                    )
//                }
//            }

            // Button to open the image picker
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = dimens.small1),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Button(
//                    onClick = { launcher.launch("image/*") },
//                    modifier = Modifier.padding(dimens.small3)
//                ) {
//                    Text("Select Photo")
//                }
//
//                // Button to clear the selected image
//                if (photoUri != null) {
//                    Button(
//                        onClick = { photoUri = null },
//                        modifier = Modifier
//                            .padding(dimens.small3)
//                    ) {
//                        Text("Clear")
//                    }
//                }
//            }

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
                  val userRegistrationData = UserData(0, name, email, password, "")
                  registrationViewModel.registerUser(userRegistrationData)
                },

            )
            NavigationText(
                text = "Already have an account? Login",
                onClick = {
//                    navController.navigate("login")
                }
            )
        }
    }
}


















