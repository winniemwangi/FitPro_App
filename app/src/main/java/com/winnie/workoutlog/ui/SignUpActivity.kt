package com.winnie.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.winnie.workoutlog.api.APIClient
import com.winnie.workoutlog.api.ApiInterface
import com.winnie.workoutlog.databinding.ActivitySignUpBinding
import com.winnie.workoutlog.models.RegisterRequest
import com.winnie.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewmodels.UserViewModel

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
    }

    fun castViews(){


        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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

        var lastname= binding.etLastName.text.toString()
        if (lastname.isBlank()){
            binding.tilLastName.error  = ("SecondName required")
            error = true
        }

        var phoneNumber = binding.etPhoneNumber.text.toString()
        if (phoneNumber.isBlank()){
            binding.tilPhoneNumber.error  = ("PhoneNumber required")
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

        if (password!=confirm){
            error = true
            binding.tilConfirm.error = ("Doesn't match")

        }

        if (!error){
            var registerRequest = RegisterRequest(firstname, lastname, phoneNumber,email,password)
//            makeRegisterRequest(registerRequest)
            userViewModel.registerUser(registerRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer {
            registerResponse->
            Toast.makeText(baseContext,registerResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        })

        userViewModel.registerErrorLiveData.observe(this, Observer {
            errorMsg->
            Toast.makeText(baseContext,errorMsg,Toast.LENGTH_LONG).show()
        })
    }
}