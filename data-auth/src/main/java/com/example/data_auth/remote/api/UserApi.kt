package com.example.data_auth.remote.api

import com.example.data_auth.remote.request.SignInRequest
import com.example.data_auth.remote.response.SignInResponse
import retrofit2.http.POST

interface UserApi {

    @POST("/in")
    suspend fun signIn(
        signInRequest: SignInRequest
    ): SignInResponse
}