package com.dwarvesv.mvvm.data.response

import com.dwarvesv.mvvm.data.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("data") var user: User
)