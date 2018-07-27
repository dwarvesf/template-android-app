package com.dwarvesv.mvp.data.response

import com.dwarvesv.mvp.data.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("data") var user: User
)