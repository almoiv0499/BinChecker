package ru.bin.domain.repository

import ru.bin.domain.model.BinDomain
import ru.bin.domain.model.BinInformationDomain

interface BinRepository {

    suspend fun getBankInfoByBin(binNumber: String): BinInformationDomain

    suspend fun addBinToDatabase(bin: BinDomain)

    suspend fun deleteBinFromDatabase(binId: Long)

}