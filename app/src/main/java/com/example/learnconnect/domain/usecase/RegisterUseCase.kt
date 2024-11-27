package com.example.learnconnect.domain.usecase

import com.example.learnconnect.domain.repository.AuthRepository
import com.example.learnconnect.domain.User
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (!email.isValidEmail()) {
            return Result.failure(Exception("Invalid email format"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        return authRepository.signUp(email, password)
    }

    private fun String.isValidEmail() = 
        Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matcher(this).matches()
}