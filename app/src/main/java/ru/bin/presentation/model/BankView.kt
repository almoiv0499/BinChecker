package ru.bin.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankView(
    val bankCity: String?,
    val bankName: String?,
    val bankPhone: String?,
    val bankUrl: String?
) : Parcelable