package ru.bin.data.repository

import ru.bin.data.database.BinDao
import ru.bin.data.mapper.mapToBin
import ru.bin.data.mapper.mapToBinInformationDomain
import ru.bin.data.network.Api
import ru.bin.domain.model.BinDomain
import ru.bin.domain.model.BinInformationDomain
import ru.bin.domain.repository.BinRepository
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val api: Api,
    private val binDao: BinDao
) : BinRepository {

    override suspend fun getBankInfoByBin(binNumber: String): BinInformationDomain =
        api.getBankInformationByBin(binNumber).mapToBinInformationDomain()

    override suspend fun addBinToDatabase(bin: BinDomain) {
        binDao.addBinToDatabase(bin.mapToBin())
    }

    override suspend fun deleteBinFromDatabase(binId: Long) {
        binDao.deleteBinFromDatabase(binId)
    }
}