package ru.bin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.bin.domain.usecase.GetBankInfoByBinUseCase
import ru.bin.presentation.extension.mapToBinInformationView
import ru.bin.presentation.model.BinInformationView
import javax.inject.Inject

class BankInformationViewModel @Inject constructor(
    private val getBankInfoByBinUseCase: GetBankInfoByBinUseCase
) : ViewModel() {

    private val _bankInformationLiveData = MutableLiveData<BinInformationView>()
    val bankInformationLiveData: LiveData<BinInformationView> = _bankInformationLiveData

    private val _exceptionLiveData = MutableLiveData<String>()
    val exceptionLiveData: LiveData<String> = _exceptionLiveData

    fun getBankInformation(binNumber: String) {
        viewModelScope.launch {
            try {
                _bankInformationLiveData.value =
                    getBankInfoByBinUseCase(binNumber).mapToBinInformationView()
            } catch (exception: HttpException) {
                _exceptionLiveData.value = exception.message()
            }
        }
    }

}