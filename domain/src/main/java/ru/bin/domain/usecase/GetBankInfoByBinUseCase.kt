package ru.bin.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.bin.domain.model.BinInformationDomain
import ru.bin.domain.repository.BinRemoteRepository
import javax.inject.Inject

class GetBankInfoByBinUseCase @Inject constructor(
    private val repository: BinRemoteRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(binNumber: String): BinInformationDomain =
        withContext(ioDispatcher) {
            repository.getBankInfoByBin(binNumber)
        }

}