package com.fahad.kotlinnodeauthenticator.ui.screen.login
import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationViewModel
import com.fahad.kotlinnodeauthenticator.model.UserData
import com.fahad.kotlinnodeauthenticator.model.UserLoginData

@Composable
fun LoginScreen1(loginViewModel: LoginViewModel = viewModel()) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }



  val loginResult by loginViewModel.loginResult.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // UI components for collecting user input (email, password)
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
    GradientButton(
      onClick = {
        val userLoginData = UserLoginData(email, password)
        loginViewModel.loginUser(userLoginData)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      text = "Login",





    )



    loginResult?.let {
      if (it.success) {
        // Handle successful login
        Toast.makeText(LocalContext.current, "Login Successful!", Toast.LENGTH_SHORT).show()
        Text(text = "Login Successful!")
      } else {
        // Handle login error
        Toast.makeText(LocalContext.current, it.errorMessage ?: "Unknown error", Toast.LENGTH_SHORT).show()
        Text(text = it.errorMessage ?: "Unknown error")
      }
    }
  }
}


@Composable
fun GradientButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit
) {
  Button(
    onClick = { onClick() },
    modifier = modifier
      .height(48.dp)
      .clip(shape = RoundedCornerShape(40.dp)),  // Apply shape here
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,
      contentColor = Color.Transparent,
    ),
    contentPadding = PaddingValues(0.dp),
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3)),

          )
        )
        .clip(shape = RoundedCornerShape(40.dp))
        .padding(horizontal = 16.dp, vertical = 8.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = text,
        style = TextStyle(
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      )
    }
  }
}





@Composable
fun CustomInputField(
  type: String = "uid",
  inputValue: MutableState<String> = remember {
    mutableStateOf("")
  },
  modifier: Modifier = Modifier,
  iconColor: MutableState<Color> = remember {
    mutableStateOf(Color(0xFFC9C9C9))
  },
  seePasswordToggle: MutableState<Boolean> = remember {
    mutableStateOf(false)
  }
) {

  val focusRequester = FocusRequester()
  val customTextSelectionColors = TextSelectionColors(
    handleColor = Color(0xFFF48FB1),
    backgroundColor = Color(0xA1F48FB1)
  )
  val rippleColor = rememberRipple(color = Color(0xFFF48FB1))

  CompositionLocalProvider(
    LocalTextSelectionColors provides customTextSelectionColors,
    LocalIndication provides rippleColor
  ) {
    OutlinedTextField(
      value = inputValue.value,
      onValueChange = { inputValue.value = it },
      modifier = modifier
        .height(72.dp)
        .focusRequester(focusRequester)
        .onFocusChanged {
          iconColor.value = if (it.isFocused) Color(0xFFF06292)
          else Color(0xFFC9C9C9)
        },
      label = {
        if (type == "uid")
          Text(text = "Username")
        else
          Text(text = "Password")
      },
      leadingIcon = {
        if (type == "uid") {
          Icon(
            imageVector = Icons.Rounded.AccountBox,
            contentDescription = "Login"
          )
        } else {
          Icon(
            imageVector = Icons.Rounded.Lock,
            contentDescription = "Password"
          )
        }
      },
      visualTransformation =
      if (type == "password") {
        if (!seePasswordToggle.value) {
          PasswordVisualTransformation()
        } else {
          VisualTransformation.None
        }
      } else {
        VisualTransformation.None
      },
      trailingIcon = {
        if (type == "password") Icon(
          imageVector = if (seePasswordToggle.value) Icons.Rounded.Lock
          else Icons.Rounded.Lock,
          contentDescription = "Trailing Icon",
          modifier = Modifier
            .size(20.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable { seePasswordToggle.value = !seePasswordToggle.value }

        ) else {
        }
      },
      shape = RoundedCornerShape(25.dp),
      singleLine = true,


      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = if (type == "uid") ImeAction.Next else ImeAction.Go
      )

    )
  }
}