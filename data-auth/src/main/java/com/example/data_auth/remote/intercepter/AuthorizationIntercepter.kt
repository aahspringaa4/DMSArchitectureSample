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

        val expiredAt = authDataStorage.fetchExpiredAt()
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = authDataStorage.fetchRefreshToken()

            val tokenRefreshRequest = Request.Builder()
                .url("http://androidonetop/auth")
                .patch(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("refresh_token", "Bearer $refreshToken")
                .build()
            val response = client.newCall(tokenRefreshRequest).execute()

            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body()!!.toString(),
                    TokenRefreshResponse::class.java
                )
                authDataStorage.setAccessToken(token.accessToken)
                authDataStorage.setRefreshToken(token.refreshToken)
                authDataStorage.setExpiredAt(token.expiredAt)
            } else throw NeedLoginException()
        }

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
        @SerializedName("refresh_token") val refreshToken: String,
        @SerializedName("expired_at") val expiredAt: String,
    )
}