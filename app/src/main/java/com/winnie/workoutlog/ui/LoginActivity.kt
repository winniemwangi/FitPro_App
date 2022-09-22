package com.winnie.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.winnie.workoutlog.api.APIClient
import com.winnie.workoutlog.api.ApiInterface
import com.winnie.workoutlog.databinding.ActivityLoginBinding
import com.winnie.workoutlog.models.LoginRequest
import com.winnie.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewmodels.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs : SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()

        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            saveLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })

    }
    fun castviews() {

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener { validateLogin() }
    }

    fun validateLogin() {
        var error = false
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Invalid Email Address"
            error = true
        }

        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error) {
            val loginRequest = LoginRequest(email, password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }


    }

    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }

}
