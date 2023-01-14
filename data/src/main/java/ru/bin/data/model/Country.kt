package ru.bin.data.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

data class Country(
    @Nullable @SerializedName("alpha2") val countyCode: String,
    @Nullable @SerializedName("currency") val countyCurrency: String,
    @Nullable val latitude: Int,
    @Nullable val longitude: Int,
    @Nullable @SerializedName("name") val countyName: String
)