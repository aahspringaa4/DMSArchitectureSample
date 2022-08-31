package com.example.database.datasource

import com.example.database.param.LocalUserViewParam

interface LocalUserDataSource {

    suspend fun fetchViewBoolean(localUserViewParam: LocalUserViewParam)
}