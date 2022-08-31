package com.example.database.datasource

import com.example.database.param.LocalUserViewParam
import com.example.database.storage.AuthDataStorage
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    val authDataStorage: AuthDataStorage
): LocalUserDataSource {
    override suspend fun fetchViewBoolean(
        localUserViewParam: LocalUserViewParam
    ) {
        authDataStorage.setViewBoolean(localUserViewParam)
    }
}