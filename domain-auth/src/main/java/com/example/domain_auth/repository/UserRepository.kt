package com.example.domain_auth.repository

import com.example.domain_auth.param.LoginParam

interface UserRepository {
    suspend fun login(loginParam: LoginParam)
}