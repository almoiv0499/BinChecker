package ru.bin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bin.data.repository.GetBinNumbersRepository
import ru.bin.domain.usecase.DeleteBinFromDatabaseUseCase
import ru.bin.presentation.extension.mapToBinView
import ru.bin.presentation.model.BinView
import javax.inject.Inject

class StoredBinNumbersViewModel @Inject constructor(
    private val deleteBinFromDatabaseUseCase: DeleteBinFromDatabaseUseCase,
    repository: GetBinNumbersRepository
) : ViewModel() {

    val getListStoredBinNumbers: LiveData<List<BinView>> =
        repository.getListStoredBinNumbers().map { list ->
            list.map { bin ->
                bin.mapToBinView()
            }
        }

    fun deleteBinFromDatabase(binId: Long) {
        viewModelScope.launch {
            deleteBinFromDatabaseUseCase(binId = binId)
        }
    }

}