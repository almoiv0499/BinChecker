package ru.bin.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bin.data.database.BinDao
import ru.bin.data.model.mapper.mapToBin
import ru.bin.data.model.mapper.mapToBinDomain
import ru.bin.domain.model.BinDomain
import ru.bin.domain.repository.BinDatabaseRepository
import javax.inject.Inject

class BinDatabaseRepositoryImpl @Inject constructor(
    private val binDao: BinDao
) : BinDatabaseRepository {

    override suspend fun addBinToDatabase(bin: BinDomain) {
        binDao.addBinToDatabase(bin.mapToBin())
    }

    override suspend fun deleteBinFromDatabase(binId: Long) {
        binDao.deleteBinFromDatabase(binId)
    }

    override fun getStoredBinNumbers(): Flow<List<BinDomain>> =
        binDao.getListStoredBinNumbers().map { list ->
            list.map { bin ->
                bin.mapToBinDomain()
            }
        }

}