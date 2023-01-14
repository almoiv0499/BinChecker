package ru.bin.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinInformationView(
    val bank: BankView?,
    val cardBrand: String?,
    val country: CountryView?,
    val cardInfo: CardInfoView?,
    val cardPrepaid: Boolean?,
    val cardScheme: String?,
    val cardType: String?
) : Parcelable