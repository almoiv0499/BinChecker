package ru.bin.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.bin.domain.repository.BinDatabaseRepository
import javax.inject.Inject

class DeleteBinFromDatabaseUseCase @Inject constructor(
    private val repository: BinDatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(binId: Long) = withContext(ioDispatcher) {
        repository.deleteBinFromDatabase(binId)
    }

}