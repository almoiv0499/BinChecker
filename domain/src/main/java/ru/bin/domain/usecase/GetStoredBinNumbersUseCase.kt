package ru.bin.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.bin.domain.model.BinDomain
import ru.bin.domain.repository.BinDatabaseRepository
import javax.inject.Inject

class GetStoredBinNumbersUseCase @Inject constructor(
    private val repository: BinDatabaseRepository
) {

    fun getStoredBinNumbers(): Flow<List<BinDomain>> = repository.getStoredBinNumbers()

}