package com.winnie.workoutlog.api

import com.winnie.workoutlog.models.LoginRequest
import com.winnie.workoutlog.models.LoginResponse
import com.winnie.workoutlog.models.RegisterRequest
import com.winnie.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>




}