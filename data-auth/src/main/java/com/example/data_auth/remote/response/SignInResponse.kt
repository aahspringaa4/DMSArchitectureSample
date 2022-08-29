package com.example.data_auth.remote.response

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("meal") val meal: Boolean,
    @SerializedName("point") val point: Boolean,
    @SerializedName("notice") val notice: Boolean
)
