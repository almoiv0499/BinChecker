package ru.bin.presentation.extension

import ru.bin.domain.model.BinDomain
import ru.bin.domain.model.BinInformationDomain
import ru.bin.presentation.model.*

fun BinInformationDomain.mapToBinInformationView(): BinInformationView = BinInformationView(
    bank = BankView(
        bankCity = bank?.bankCity,
        bankName = bank?.bankName,
        bankPhone = bank?.bankPhone,
        bankUrl = bank?.bankUrl
    ),
    cardBrand = cardBrand,
    country = CountryView(
        countryCode = country?.countryCode,
        countyCurrency = country?.countyCurrency,
        latitude = country?.latitude,
        longitude = country?.longitude,
        countyName = country?.countyName,
    ),
    cardInfo = CardInfoView(
        cardLength = cardInfo?.cardLength,
        luhn = cardInfo?.luhn
    ),
    cardPrepaid = cardPrepaid,
    cardScheme = cardScheme,
    cardType = cardType
)

fun BinDomain.mapToBinView(): BinView = BinView(
    id = id,
    binNumber = binNumber
)

fun BinView.mapToBinDomain(): BinDomain = BinDomain(
    id = id,
    binNumber = binNumber
)