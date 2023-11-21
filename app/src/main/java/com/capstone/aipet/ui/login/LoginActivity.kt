package com.capstone.aipet.ui.login

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
import com.capstone.aipet.DashboardActivity
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.ui.register.SignUpActivity
import com.capstone.aipet.customview.button.ButtonLogin
import com.capstone.aipet.customview.button.EditTextEmail
import com.capstone.aipet.customview.button.EditTextPassword
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.login.ResponseLogin
import com.capstone.aipet.databinding.ActivityLoginBinding
import com.capstone.aipet.pref.UserPreference

class LoginActivity : AppCompatActivity() {

    private lateinit var  buttonLogin: ButtonLogin
    private lateinit var  editTextEmail: EditTextEmail
    private lateinit var  editTextPassword: EditTextPassword
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonLogin = binding.loginButton
        editTextEmail = binding.emailEditText
        editTextPassword = binding.passwordEditText

        binding.btnIntent.setOnClickListener{
            onSignUpClick()
        }
        binding.btnTest.setOnClickListener{
            onTestClick()
        }
        ButtonEnable()
        setupAction()


    }

    private fun onTestClick(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onSignUpClick(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
    private fun setButtonLoginEnable() {
        val resultEmail = editTextEmail.text
        val resultPassword = editTextPassword.text
        buttonLogin.isEnabled = !resultEmail.isNullOrEmpty() && !resultPassword.isNullOrEmpty()
    }
    private fun ButtonEnable(){
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonLoginEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonLoginEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun setupAction() {
        buttonLogin.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            loginViewModel.login(email, password).observe(this) { DataResult ->
                if (DataResult != null) {
                    when(DataResult) {
                        is DataResult.Loading -> {
                            showLoading(true)
                        }
                        is DataResult.Success -> {
                            processLogin(DataResult.data)
                            showLoading(false)
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this, getString(R.string.login_sukses), Toast.LENGTH_LONG).show()
                        }
                        is DataResult.Error -> {
                            showLoading(false)
                            Toast.makeText(this,
                                getString(R.string.periksa_kembali_pengisian_anda), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun processLogin(data: ResponseLogin) {
        if (data.errors) {
            Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
        } else {
            UserPreference.saveToken(data.loginResult.token, this)
            finish()
        }
    }

    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state

    }


}