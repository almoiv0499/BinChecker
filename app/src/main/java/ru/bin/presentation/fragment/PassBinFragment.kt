package ru.bin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.bin.app.MyApplication
import ru.bin.presentation.R
import ru.bin.presentation.databinding.FragmentPassBinBinding
import ru.bin.presentation.viewmodel.PassBinViewModel
import ru.bin.presentation.viewmodelfactory.BinViewModelFactory
import javax.inject.Inject

class PassBinFragment : Fragment() {

    companion object {
        private const val BIN_NUMBER_LENGTH = 8
    }

    private var _binding: FragmentPassBinBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
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

        binding.buttonBankInfo.setOnClickListener {
            val binNumber = binding.inputBinNumber.text.toString()
            checkLength(binNumber)
        }
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
            Toast.makeText(
                requireActivity(),
                requireActivity().getString(R.string.bin_number_length),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            navigateToBankInformationFragment(binNumber)
        }
    }

    private fun navigateToBankInformationFragment(binNumber: String) {
        viewModel.addBinNumberToDatabase(binNumber)
        findNavController().navigate(
            PassBinFragmentDirections.actionPassBinFragmentToInformationFragment(binNumber)
        )
    }

    private fun navigateToStoredBinNumbersFragment() {
        findNavController().navigate(R.id.action_passBinFragment_to_storedBinNumbersFragmentFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}