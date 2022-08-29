package com.example.data_auth.remote.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)