package com.example.database.storage

import com.example.database.param.LocalUserViewParam
import java.time.LocalDateTime

interface AuthDataStorage {

    fun setAccessToken(token: String)
    fun fetchAccessToken(): String
    fun clearAccessToken()

    fun setRefreshToken(token: String)
    fun fetchRefreshToken(): String
    fun clearRefreshToken()

    fun setViewBoolean(localUserViewParam: LocalUserViewParam)
    fun fetchViewBoolean()
}