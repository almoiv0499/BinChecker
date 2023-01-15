package ru.bin.domain.repository

import ru.bin.domain.model.BinInformationDomain

interface BinRemoteRepository {

    suspend fun getBankInfoByBin(binNumber: String): BinInformationDomain

}