package com.example.data_auth.remote.intercepter

import com.example.database.storage.AuthDataStorage
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.domain_auth.exception.NeedLoginException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.time.LocalDateTime
import java.time.ZoneId

class AuthorizationInterceptor(
    private val authDataStorage: AuthDataStorage
) : Interceptor {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/in"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)
        if (path == "/users" && request.method() == "POST") return chain.proceed(request)
        if (path.contains("/users/accounts/")) return chain.proceed(request)

        val accessToken = authDataStorage.fetchAccessToken()
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }

    data class TokenRefreshResponse(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("refresh_token") val refreshToken: String
    )
}