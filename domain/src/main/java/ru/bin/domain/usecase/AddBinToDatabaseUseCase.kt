package ru.bin.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.bin.domain.model.BinDomain
import ru.bin.domain.repository.BinRepository
import javax.inject.Inject

class AddBinToDatabaseUseCase @Inject constructor(
    private val repository: BinRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(bin: BinDomain) = withContext(ioDispatcher) {
        repository.addBinToDatabase(bin)
    }
}