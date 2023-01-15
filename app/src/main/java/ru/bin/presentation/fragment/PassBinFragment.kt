package ru.bin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.bin.app.MyApplication
import ru.bin.presentation.R
import ru.bin.presentation.databinding.FragmentPassBinBinding
import ru.bin.presentation.fragment.basefragment.BaseFragment
import ru.bin.presentation.fragment.viewmodel.PassBinViewModel
import ru.bin.presentation.fragment.viewmodelfactory.BinViewModelFactory
import javax.inject.Inject

class PassBinFragment : BaseFragment<PassBinViewModel>() {

    companion object {
        private const val BIN_NUMBER_LENGTH = 8
    }

    private var _binding: FragmentPassBinBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory

    override val viewModel: PassBinViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity(), viewModelFactory)[PassBinViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().applicationContext as MyApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPassBinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenerButtonNavigateToBankInformationFragment()
        listenerButtonNavigateToStoredBinNumbersFragment()

    }

    private fun listenerButtonNavigateToBankInformationFragment() {
        binding.buttonBankInfo.setOnClickListener {
            val binNumber = binding.inputBinNumber.text.toString()
            checkLength(binNumber)
        }
    }

    private fun listenerButtonNavigateToStoredBinNumbersFragment() {
        binding.bottomNavigationFragmentPass.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list_bin_numbers -> {
                    navigateToStoredBinNumbersFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun checkLength(binNumber: String) {
        if (binNumber.length != BIN_NUMBER_LENGTH) {
            viewModel.exceptionMessage()
        } else {
            navigateToBankInformationFragment(binNumber)
        }
    }

    private fun navigateToBankInformationFragment(binNumber: String) {
        viewModel.addBinNumberToDatabase(binNumber)
        viewModel.navigateToBankInformationFragment(binNumber)
    }

    private fun navigateToStoredBinNumbersFragment() {
        viewModel.navigateToStoredBinNumbers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}