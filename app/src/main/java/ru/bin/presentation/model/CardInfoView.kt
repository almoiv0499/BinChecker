package ru.bin.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfoView(
    val cardLength: Int?,
    val luhn: Boolean?
) : Parcelable