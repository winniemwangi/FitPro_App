package com.winnie.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.winnie.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
     lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
    }

    fun castviews(){

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener { validateLogin() }
    }

    fun validateLogin(){
        var error = false
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        var email = binding.etEmail.text.toString()
        if (email.isBlank()){
            binding.tilEmail.error = "Email is required"
            error = true
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.error = "Invalid Email Address"
            error = true
        }

        var password = binding.etPassword.text.toString()
        if (password.isBlank()){
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error){
            startActivity(Intent(this, HomeActivity::class.java))
        }
//        else
//        {
//            tilPassword.error = null
//        }
    }



    }
