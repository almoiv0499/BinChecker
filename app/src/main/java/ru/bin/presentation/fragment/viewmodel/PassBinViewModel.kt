package ru.bin.presentation.fragment.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bin.domain.usecase.AddBinToDatabaseUseCase
import ru.bin.presentation.R
import ru.bin.presentation.fragment.PassBinFragmentDirections
import ru.bin.presentation.fragment.viewmodel.baseviewmodel.BaseViewModel
import ru.bin.presentation.model.BinView
import ru.bin.presentation.model.extension.mapToBinDomain
import javax.inject.Inject

class PassBinViewModel @Inject constructor(
    private val addBinToDatabaseUseCase: AddBinToDatabaseUseCase
) : BaseViewModel() {

    fun addBinNumberToDatabase(binNumber: String) {
        viewModelScope.launch {
            val bin = BinView(binNumber = binNumber)
            addBinToDatabaseUseCase(bin.mapToBinDomain())
        }
    }

    fun navigateToBankInformationFragment(binNumber: String) {
        navigate(
            PassBinFragmentDirections
                .actionPassBinFragmentToInformationFragment(binNumber)
        )
    }

    fun navigateToStoredBinNumbers() {
        navigate(
            PassBinFragmentDirections
                .actionPassBinFragmentToStoredBinNumbersFragment()
        )
    }

    fun exceptionMessage() {
        exceptionMessage(R.string.bin_number_length)
    }

}