package com.winnie.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var btnSignup : Button
    lateinit var etFirstName : TextInputEditText
    lateinit var tilFirstName: TextInputLayout
    lateinit var etSecondName : TextInputEditText
    lateinit var tilSecondName:TextInputLayout
    lateinit var etEmailAddress : TextInputEditText
    lateinit var tilEmailAddress : TextInputLayout
    lateinit var etPassword : TextInputEditText
    lateinit var tilPassword : TextInputLayout
    lateinit var etConfirm : TextInputEditText
    lateinit var tilConfirm : TextInputLayout
    lateinit var tvLogin :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        castViews()
    }

    fun castViews(){
        btnSignup  = findViewById(R.id.btnSignup)
        etFirstName  = findViewById(R.id.etFirstName)
        tilFirstName  = findViewById(R.id.tilFirstName)
        etSecondName  = findViewById(R.id.etSecondName)
        tilSecondName  = findViewById(R.id.tilSecondName)
        etEmailAddress  = findViewById(R.id.etEmailAddress)
        tilEmailAddress  = findViewById(R.id.tilEmailAddress)
        etPassword  = findViewById(R.id.etPassword)
        tilPassword  = findViewById(R.id.tilPassword)
        etConfirm  = findViewById(R.id.etConfirm)
        tilConfirm = findViewById(R.id.tilConfirm)
        tvLogin = findViewById(R.id.tvLogin)

        tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener { validateSignup() }

    }

    fun validateSignup(){
        var error = false
        var firstname = etFirstName.text.toString()
        if (firstname.isBlank()){
            tilFirstName.error  = ("FirstName required")
            error = true
        }

        var secondname = etSecondName.text.toString()
        if (secondname.isBlank()){
            tilSecondName.error  = ("SecondName required")
            error = true
        }

        var email = etEmailAddress.text.toString()
        if (email.isBlank()){
            tilEmailAddress.error  = ("EmailAddress required")
            error = true
        }

        var password = etPassword.text.toString()
        if (password.isBlank()){
            tilPassword.error  = ("Password required")
            error = true
        }

        var confirm = etConfirm.text.toString()
        if (confirm.isBlank()){
            tilConfirm.error  = ("Confirm Password required")
            error = true
        }

        if (password!=confirm)
            tilConfirm.error = ("Doesn't match")

    }
}