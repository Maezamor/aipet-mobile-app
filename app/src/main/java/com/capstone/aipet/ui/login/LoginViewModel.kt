package com.capstone.aipet.ui.login

import androidx.lifecycle.ViewModel
import com.capstone.aipet.data.repository.UserRepository

class LoginViewModel (private val repository: UserRepository) : ViewModel() {
    fun login(email: String, password: String) = repository.loginRepo(email, password)
}