package com.winnie.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.winnie.workoutlog.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
    }

    fun castViews(){


        binding.tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener { validateSignup() }

    }

    fun validateSignup(){
        var error = false
        var firstname = binding.etFirstName.text.toString()
        if (firstname.isBlank()){
            binding.tilFirstName.error  = ("FirstName required")
            error = true
        }

        var secondname = binding.etSecondName.text.toString()
        if (secondname.isBlank()){
            binding.tilSecondName.error  = ("SecondName required")
            error = true
        }

        var email = binding.etEmailAddress.text.toString()
        if (email.isBlank()){
            binding.tilEmailAddress.error  = ("EmailAddress required")
            error = true
        }

        var password = binding.etPassword.text.toString()
        if (password.isBlank()){
            binding.tilPassword.error  = ("Password required")
            error = true
        }

        var confirm = binding.etConfirm.text.toString()
        if (confirm.isBlank()){
            binding.tilConfirm.error  = ("Confirm Password required")
            error = true
        }

        if (password!=confirm)
            binding.tilConfirm.error = ("Doesn't match")

    }
}