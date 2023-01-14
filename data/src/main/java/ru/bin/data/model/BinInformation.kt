package ru.bin.data.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

data class BinInformation(
    @Nullable val bank: Bank,
    @Nullable @SerializedName("brand") val cardBrand: String,
    @Nullable val country: Country,
    @Nullable @SerializedName("number") val cardInfo: CardInfo,
    @Nullable @SerializedName("prepaid") val cardPrepaid: Boolean,
    @Nullable @SerializedName("scheme") val cardScheme: String,
    @Nullable @SerializedName("type") val cardType: String
)