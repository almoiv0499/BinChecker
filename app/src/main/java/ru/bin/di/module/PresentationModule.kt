package ru.bin.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bin.di.annotation.ViewModelKey
import ru.bin.presentation.fragment.viewmodel.BankInformationViewModel
import ru.bin.presentation.fragment.viewmodel.PassBinViewModel
import ru.bin.presentation.fragment.viewmodel.StoredBinNumbersViewModel

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(BankInformationViewModel::class)]
    fun bindBankInformationViewModel(viewModel: BankInformationViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(PassBinViewModel::class)]
    fun bindPassBinViewModel(viewModel: PassBinViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(StoredBinNumbersViewModel::class)]
    fun bindStoredBinNumbersViewModel(viewModel: StoredBinNumbersViewModel): ViewModel

}