package ru.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bin.domain.usecase.AddBinToDatabaseUseCase
import ru.bin.presentation.extension.mapToBinDomain
import ru.bin.presentation.model.BinView
import javax.inject.Inject

class PassBinViewModel @Inject constructor(
    private val addBinToDatabaseUseCase: AddBinToDatabaseUseCase
) : ViewModel() {

    fun addBinNumberToDatabase(binNumber: String) {
        viewModelScope.launch {
            val bin = BinView(binNumber = binNumber)
            addBinToDatabaseUseCase(bin.mapToBinDomain())
        }
    }

}