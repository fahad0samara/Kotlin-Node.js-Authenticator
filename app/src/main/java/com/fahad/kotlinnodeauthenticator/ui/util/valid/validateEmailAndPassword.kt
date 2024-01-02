package com.fahad.kotlinnodeauthenticator.ui.util.valid

import com.fahad.kotlinnodeauthenticator.model.OperationResult

fun validateEmailAndPassword(email: String, password: String): OperationResult {
  if (email.isBlank()) {
    return OperationResult(success = false, errorMessage = "Email cannot be blank")
  }

  if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
    return OperationResult(success = false, errorMessage = "Invalid email format")
  }

  if (password.isBlank()) {
    return OperationResult(success = false, errorMessage = "Password cannot be blank")
  }

  if (password.length < 6) {
    return OperationResult(success = false, errorMessage = "Password must be at least 6 characters long")
  }

  if (!password.any { it.isUpperCase() }) {
    return OperationResult(success = false, errorMessage = "Password must contain at least one uppercase letter")
  }



  return OperationResult(success = true, errorMessage = null)
}

