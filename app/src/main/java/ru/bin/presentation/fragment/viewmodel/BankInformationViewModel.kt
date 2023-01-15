package ru.bin.presentation.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.bin.domain.usecase.GetBankInfoByBinUseCase
import ru.bin.presentation.R
import ru.bin.presentation.fragment.viewmodel.baseviewmodel.BaseViewModel
import ru.bin.presentation.model.BinInformationView
import ru.bin.presentation.model.extension.mapToBinInformationView
import javax.inject.Inject

class BankInformationViewModel @Inject constructor(
    private val getBankInfoByBinUseCase: GetBankInfoByBinUseCase
) : BaseViewModel() {

    private val _bankInformationLiveData = MutableLiveData<BinInformationView>()
    val bankInformationLiveData: LiveData<BinInformationView> = _bankInformationLiveData

    fun getBankInformation(binNumber: String) {
        viewModelScope.launch {
            try {
                _bankInformationLiveData.value =
                    getBankInfoByBinUseCase(binNumber).mapToBinInformationView()
            } catch (exception: HttpException) {
                exceptionMessage(R.string.exception_load_info)
            }
        }
    }

}