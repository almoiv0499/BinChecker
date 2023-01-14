package ru.bin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.bin.data.database.BinDao
import ru.bin.data.mapper.mapToBinDomain
import ru.bin.domain.model.BinDomain
import javax.inject.Inject

class GetBinNumbersRepository @Inject constructor(
    private val binDao: BinDao
) {

    fun getListStoredBinNumbers(): LiveData<List<BinDomain>> =
        Transformations.map(binDao.getListStoredBinNumbers()) { list ->
            list.map { it.mapToBinDomain() }
        }

}