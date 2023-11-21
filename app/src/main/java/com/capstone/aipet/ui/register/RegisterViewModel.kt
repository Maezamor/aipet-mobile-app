package com.capstone.aipet.ui.register

import androidx.lifecycle.ViewModel
import com.capstone.aipet.data.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {
    fun register(username: String,name: String,address: String,phone: String, email: String, password: String) = userRepository.registerRepo(username, name, address, phone, email, password)
}