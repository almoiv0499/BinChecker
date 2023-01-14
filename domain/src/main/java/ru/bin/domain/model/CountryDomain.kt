package ru.bin.domain.model

data class CountryDomain(
    val countryCode: String?,
    val countyCurrency: String?,
    val latitude: Int?,
    val longitude: Int?,
    val countyName: String?
)