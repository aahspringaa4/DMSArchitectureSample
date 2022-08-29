package com.example.data_auth.remote.datasource.declaration

import com.example.data_auth.remote.api.UserApi
import com.example.data_auth.remote.datasource.implementation.RemoteUserDataSource
import com.example.data_auth.remote.request.SignInRequest
import com.example.data_auth.remote.response.SignInResponse
import com.example.data_auth.util.HttpHandler
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
): RemoteUserDataSource {
    override suspend fun postUserSignIn(
        signInRequest: SignInRequest
    ) = HttpHandler<SignInResponse>()
        .httpRequest { userApi.signIn(signInRequest) }
        .sendRequest()
}