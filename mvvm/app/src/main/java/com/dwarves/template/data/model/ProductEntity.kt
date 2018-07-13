package com.dwarves.template.data.model

import com.google.gson.annotations.SerializedName

data class ProductEntity(
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String
)
