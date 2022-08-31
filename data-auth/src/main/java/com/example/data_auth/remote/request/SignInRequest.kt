package com.example.data_auth.remote.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("password") val password: String
)