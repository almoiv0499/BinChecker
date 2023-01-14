package ru.bin.data.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

data class Bank(
    @Nullable @SerializedName("city") val bankCity: String,
    @Nullable @SerializedName("name") val bankName: String,
    @Nullable @SerializedName("phone") val bankPhone: String,
    @Nullable @SerializedName("url") val bankUrl: String
)