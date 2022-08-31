package com.example.data_auth.remote.response

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @field:SerializedName("meal") val meal: Boolean,
    @field:SerializedName("point") val point: Boolean,
    @field:SerializedName("notice") val notice: Boolean
)
