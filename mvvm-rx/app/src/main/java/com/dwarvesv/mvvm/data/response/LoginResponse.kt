package {{packageName}}.data.response

import {{packageName}}.data.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("data") var user: User
)