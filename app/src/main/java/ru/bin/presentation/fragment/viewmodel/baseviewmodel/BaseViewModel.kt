package ru.bin.presentation.fragment.viewmodel.baseviewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ru.bin.presentation.fragment.util.Event
import ru.bin.presentation.fragment.util.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    private val _navigationLiveData = MutableLiveData<Event<NavigationCommand>>()
    val navigationLiveData: LiveData<Event<NavigationCommand>> = _navigationLiveData

    private val _exceptionLiveData = MutableLiveData<Event<Int>>()
    val exceptionLiveData: LiveData<Event<Int>> = _exceptionLiveData

    fun navigate(navDirections: NavDirections) {
        _navigationLiveData.value = Event(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigationLiveData.value = Event(NavigationCommand.Back)
    }

    fun exceptionMessage(@StringRes message: Int) {
        _exceptionLiveData.value = Event(message)
    }

}