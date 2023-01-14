package ru.bin.domain.model

data class BinInformationDomain(
    val bank: BankDomain?,
    val cardBrand: String?,
    val country: CountryDomain?,
    val cardInfo: CardInfoDomain?,
    val cardPrepaid: Boolean?,
    val cardScheme: String?,
    val cardType: String?
)