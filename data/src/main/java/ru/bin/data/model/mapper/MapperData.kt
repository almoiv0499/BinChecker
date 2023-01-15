package ru.bin.data.model.mapper

import ru.bin.data.model.Bin
import ru.bin.data.model.BinInformation
import ru.bin.domain.model.*

fun BinInformation.mapToBinInformationDomain(): BinInformationDomain = BinInformationDomain(
    bank = BankDomain(
        bankCity = bank.bankCity,
        bankName = bank.bankName,
        bankPhone = bank.bankPhone,
        bankUrl = bank.bankUrl
    ),
    cardBrand = cardBrand,
    country = CountryDomain(
        countryCode = country.countyCode,
        countyCurrency = country.countyCurrency,
        latitude = country.latitude,
        longitude = country.longitude,
        countyName = country.countyName
    ),
    cardInfo = CardInfoDomain(
        cardLength = cardInfo.cardLength,
        luhn = cardInfo.luhn
    ),
    cardPrepaid = cardPrepaid,
    cardScheme = cardScheme,
    cardType = cardType
)

fun BinDomain.mapToBin(): Bin = Bin(
    id = id,
    binNumber = binNumber
)

fun Bin.mapToBinDomain(): BinDomain = BinDomain(
    id = id,
    binNumber = binNumber
)
