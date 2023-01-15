package ru.bin.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bin.domain.model.BinDomain

interface BinDatabaseRepository {

    suspend fun addBinToDatabase(bin: BinDomain)

    suspend fun deleteBinFromDatabase(binId: Long)

    fun getStoredBinNumbers(): Flow<List<BinDomain>>

}