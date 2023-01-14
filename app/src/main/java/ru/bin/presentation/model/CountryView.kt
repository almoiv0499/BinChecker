package ru.bin.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryView(
    val countryCode: String?,
    val countyCurrency: String?,
    val latitude: Int?,
    val longitude: Int?,
    val countyName: String?
) : Parcelable