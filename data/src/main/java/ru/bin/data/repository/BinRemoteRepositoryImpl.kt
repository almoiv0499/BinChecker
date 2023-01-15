package ru.bin.data.repository

import ru.bin.data.model.mapper.mapToBinInformationDomain
import ru.bin.data.network.Api
import ru.bin.domain.model.BinInformationDomain
import ru.bin.domain.repository.BinRemoteRepository

class BinRemoteRepositoryImpl(
    private val api: Api
) : BinRemoteRepository {

    override suspend fun getBankInfoByBin(binNumber: String): BinInformationDomain =
        api.getBankInformationByBin(binNumber).mapToBinInformationDomain()

}