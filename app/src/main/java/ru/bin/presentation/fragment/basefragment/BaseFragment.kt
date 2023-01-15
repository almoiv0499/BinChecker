package ru.bin.presentation.fragment.basefragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bin.presentation.fragment.util.NavigationCommand
import ru.bin.presentation.fragment.viewmodel.baseviewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigation()
        setExceptionMessage()
    }

    private fun setExceptionMessage() {
        viewModel.exceptionLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.ToDirection -> {
                findNavController().navigate(navigationCommand.directions)
            }
            is NavigationCommand.Back -> {
                findNavController().navigateUp()
            }
        }
    }

}