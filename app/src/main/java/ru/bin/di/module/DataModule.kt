package ru.bin.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.bin.data.database.BinDao
import ru.bin.data.database.BinDatabase
import ru.bin.data.network.Api
import ru.bin.data.network.RetrofitInstance
import ru.bin.data.repository.BinRepositoryImpl
import ru.bin.data.repository.GetBinNumbersRepository
import ru.bin.di.annotation.ApplicationScope
import ru.bin.domain.repository.BinRepository

@Module
class DataModule {

    @Provides
    fun provideApi(): Api = RetrofitInstance.getApi()

    @ApplicationScope
    @Provides
    fun provideBinDao(
        application: Application
    ): BinDao = BinDatabase.getInstance(application = application).binDao()

    @Provides
    fun provideRepository(
        api: Api,
        binDao: BinDao
    ): BinRepository =
        BinRepositoryImpl(api = api, binDao = binDao)

    @Provides
    fun provideGetBinNumbersRepository(
        binDao: BinDao
    ): GetBinNumbersRepository = GetBinNumbersRepository(binDao = binDao)

}