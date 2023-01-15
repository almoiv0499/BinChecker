package ru.bin.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.bin.domain.repository.BinDatabaseRepository
import ru.bin.domain.repository.BinRemoteRepository
import ru.bin.domain.usecase.AddBinToDatabaseUseCase
import ru.bin.domain.usecase.DeleteBinFromDatabaseUseCase
import ru.bin.domain.usecase.GetBankInfoByBinUseCase
import ru.bin.domain.usecase.GetStoredBinNumbersUseCase

@Module
class DomainModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetBankInfoByBinUseCase(
        repository: BinRemoteRepository,
        dispatcher: CoroutineDispatcher
    ): GetBankInfoByBinUseCase =
        GetBankInfoByBinUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideAddBinToDatabaseUseCase(
        repository: BinDatabaseRepository,
        dispatcher: CoroutineDispatcher
    ): AddBinToDatabaseUseCase =
        AddBinToDatabaseUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideDeleteBinFromDatabaseUseCase(
        repository: BinDatabaseRepository,
        dispatcher: CoroutineDispatcher
    ): DeleteBinFromDatabaseUseCase =
        DeleteBinFromDatabaseUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideGetStoredBinNumbersUseCase(
        repository: BinDatabaseRepository
    ): GetStoredBinNumbersUseCase = GetStoredBinNumbersUseCase(repository = repository)
}