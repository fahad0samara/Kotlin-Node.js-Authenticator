package com.fahad.kotlinnodeauthenticator.ui.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationViewModel
import com.fahad.kotlinnodeauthenticator.model.UserData

@Composable
fun RegistrationScreen(registrationViewModel: RegistrationViewModel = viewModel()) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  val registrationResult by registrationViewModel.registrationResult.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // UI components for collecting user input (name, email, password)
    TextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Name") },
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    )

    TextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("Email") },
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    )

    TextField(
      value = password,
      onValueChange = { password = it },
      label = { Text("Password") },
      visualTransformation = PasswordVisualTransformation(),
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    )

    Button(
      onClick = {
        val userRegistrationData = UserData(0, name, email, password, "")
        registrationViewModel.registerUser(userRegistrationData)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
      Text("Register")
    }

    registrationResult?.let {
      if (it.success) {
        // Handle successful registration
        Toast.makeText(LocalContext.current, "Registration Successful!", Toast.LENGTH_SHORT).show()
      } else {
        // Handle registration error
        Toast.makeText(LocalContext.current, it.errorMessage ?: "Unknown error", Toast.LENGTH_SHORT).show()
        Text(text = it.errorMessage ?: "Unknown error")
      }
    }
  }
}

