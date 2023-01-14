package ru.bin.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.bin.domain.repository.BinRepository
import ru.bin.domain.usecase.AddBinToDatabaseUseCase
import ru.bin.domain.usecase.DeleteBinFromDatabaseUseCase
import ru.bin.domain.usecase.GetBankInfoByBinUseCase

@Module
class DomainModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetBankInfoByBinUseCase(
        repository: BinRepository,
        dispatcher: CoroutineDispatcher
    ): GetBankInfoByBinUseCase =
        GetBankInfoByBinUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideAddBinToDatabaseUseCase(
        repository: BinRepository,
        dispatcher: CoroutineDispatcher
    ): AddBinToDatabaseUseCase =
        AddBinToDatabaseUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideDeleteBinFromDatabaseUseCase(
        repository: BinRepository,
        dispatcher: CoroutineDispatcher
    ): DeleteBinFromDatabaseUseCase =
        DeleteBinFromDatabaseUseCase(repository = repository, ioDispatcher = dispatcher)

}