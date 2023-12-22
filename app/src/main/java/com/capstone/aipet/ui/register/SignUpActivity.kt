package com.capstone.aipet.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.customview.button.ButtonRegister
import com.capstone.aipet.customview.button.EditTextAddress
import com.capstone.aipet.customview.button.EditTextEmail
import com.capstone.aipet.customview.button.EditTextFullname
import com.capstone.aipet.customview.button.EditTextPassword
import com.capstone.aipet.customview.button.EditTextPhone
import com.capstone.aipet.customview.button.EditTextUsername
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.register.ResponseRegister
import com.capstone.aipet.databinding.ActivitySignUpBinding
import com.capstone.aipet.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var  buttonRegister: ButtonRegister
    private lateinit var  editTextEmail: EditTextEmail
    private lateinit var  editTextPassword: EditTextPassword
    private lateinit var  editTextUsername: EditTextUsername
    private lateinit var  editTextFullname: EditTextFullname
    private lateinit var  editTextPhone: EditTextPhone
    private lateinit var  editTextAddress: EditTextAddress
    private lateinit var  binding: ActivitySignUpBinding

    private val RegisterViewModel: RegisterViewModel by viewModels{
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
         editTextUsername = binding.usernameEditText
         editTextFullname = binding.fullnameEditText
         editTextEmail = binding.emailEditText
         editTextPassword = binding.passwordEditText
         editTextAddress = binding.addressEditText
         editTextPhone = binding.phoneEditText
         buttonRegister = binding.registerButton

        binding.btnIntent.setOnClickListener{
            onLoginClick()
        }
        ButtonRegisterEnable()
        setupAction()
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val name = binding.fullnameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val address = binding.addressEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            val inputmethodmanager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputmethodmanager.hideSoftInputFromWindow(it.windowToken, 0)
            RegisterViewModel.register(username, name, address, phone, email, password).observe(this) {
                if (it != null) {
                    when (it) {
                        is DataResult.Loading -> {
                            showLoading(true)
                        }

                        is DataResult.Success -> {
                            showLoading(false)
                            processRegister(it.data)
                        }

                        is DataResult.Error -> {
                            showLoading(false)
                            Toast.makeText(this, "Please change your email or username", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun processRegister(data: ResponseRegister) {
        if (data.itemErrors == true) {
            Toast.makeText(this,
                getString(R.string.regristasi_gagal_periksa_kembali_pengisian_anda), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,
                getString(R.string.sign_up_berhasil_silahkan_login), Toast.LENGTH_LONG).show()
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun onLoginClick(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private  fun  ButtonRegisterEnable(){
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        editTextUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        editTextFullname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonRegisterEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }
  private fun setButtonRegisterEnable(){
      val usernameResult = editTextUsername.text
      val fullnameResult = editTextFullname.text
      val phoneResult = editTextPhone.text
      val emailResult = editTextEmail.text
      val addresResult = editTextAddress.text
      val passwordResult = editTextPassword.text
      buttonRegister.isEnabled = !emailResult.isNullOrEmpty() && !passwordResult.isNullOrEmpty() &&
                                 !phoneResult.isNullOrEmpty() && !fullnameResult.isNullOrEmpty() &&
                                 !usernameResult.isNullOrEmpty() && !addresResult.isNullOrEmpty()
  }

    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }
}