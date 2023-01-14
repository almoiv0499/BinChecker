package ru.bin.data.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

data class CardInfo(
    @Nullable @SerializedName("length") val cardLength: Int,
    @Nullable val luhn: Boolean
)