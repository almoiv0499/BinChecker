package ru.bin.presentation.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bin.domain.usecase.DeleteBinFromDatabaseUseCase
import ru.bin.domain.usecase.GetStoredBinNumbersUseCase
import ru.bin.presentation.fragment.StoredBinNumbersFragmentDirections
import ru.bin.presentation.fragment.viewmodel.baseviewmodel.BaseViewModel
import ru.bin.presentation.model.BinView
import ru.bin.presentation.model.extension.mapToBinView
import javax.inject.Inject

class StoredBinNumbersViewModel @Inject constructor(
    private val deleteBinFromDatabaseUseCase: DeleteBinFromDatabaseUseCase,
    private val getStoredBinNumbersUseCase: GetStoredBinNumbersUseCase
) : BaseViewModel() {

    val getListStoredBinNumbers: LiveData<List<BinView>> =
        getStoredBinNumbersUseCase.getStoredBinNumbers().asLiveData().map { list ->
            list.map { bin ->
                bin.mapToBinView()
            }
        }

    fun deleteBinFromDatabase(binId: Long) {
        viewModelScope.launch {
            deleteBinFromDatabaseUseCase(binId = binId)
        }
    }

    fun navigateToBankInformationFragment(binNumber: String) {
        navigate(StoredBinNumbersFragmentDirections
            .actionStoredBinNumbersFragmentToInformationFragment(binNumber))
    }

    fun navigateBackToPassBinFragment() {
        navigateBack()
    }

}