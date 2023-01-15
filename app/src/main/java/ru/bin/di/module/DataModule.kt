package ru.bin.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.bin.data.database.BinDao
import ru.bin.data.database.BinDatabase
import ru.bin.data.network.Api
import ru.bin.data.network.RetrofitInstance
import ru.bin.data.repository.BinDatabaseRepositoryImpl
import ru.bin.data.repository.BinRemoteRepositoryImpl
import ru.bin.di.annotation.ApplicationScope
import ru.bin.domain.repository.BinDatabaseRepository
import ru.bin.domain.repository.BinRemoteRepository

@Module
class DataModule {

    @Provides
    fun provideApi(): Api = RetrofitInstance.getApi()

    @ApplicationScope
    @Provides
    fun provideBinDao(
        application: Application
    ): BinDao = BinDatabase.getDatabase(application = application).binDao()

    @Provides
    fun provideBinDatabaseRepository(
        binDao: BinDao
    ): BinDatabaseRepository = BinDatabaseRepositoryImpl(binDao = binDao)

    @Provides
    fun provideBinRemoteRepository(
        api: Api
    ): BinRemoteRepository = BinRemoteRepositoryImpl(api = api)

}