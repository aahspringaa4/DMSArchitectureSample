package com.example.data_auth.repository

import com.example.data_auth.remote.datasource.implementation.RemoteUserDataSource
import com.example.data_auth.remote.request.SignInRequest
import com.example.database.datasource.LocalUserDataSource
import com.example.database.param.LocalUserViewParam
import com.example.domain_auth.param.LoginParam
import com.example.domain_auth.repository.UserRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
): UserRepository{


    override suspend fun login(
        loginParam: LoginParam
    ) {
        val response = remoteUserDataSource.postUserSignIn(SignInRequest(
            id = loginParam.id,
            password = loginParam.password
        ))

        localUserDataSource.fetchViewBoolean(LocalUserViewParam(
            meal = response.meal,
            point = response.point,
            notice = response.notice
        ))
    }
}