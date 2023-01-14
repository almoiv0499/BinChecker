package ru.bin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bin.app.MyApplication
import ru.bin.presentation.R
import ru.bin.presentation.adapter.BinAdapter
import ru.bin.presentation.databinding.FragmentStoredBinNumbersBinding
import ru.bin.presentation.viewmodel.StoredBinNumbersViewModel
import ru.bin.presentation.viewmodelfactory.BinViewModelFactory
import javax.inject.Inject

class StoredBinNumbersFragment : Fragment() {

    private var _binding: FragmentStoredBinNumbersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[StoredBinNumbersViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (activity?.applicationContext as MyApplication).component
    }

    private val storedAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BinAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoredBinNumbersBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListStoredBinNumbers.observe(viewLifecycleOwner) {
            storedAdapter.submitList(it)
        }
        navigateToPassBinFragment()
        binDetailListener()
        deleteBinNumberBySwipe()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = storedAdapter
        }
    }

    private fun binDetailListener() {
        storedAdapter.toBinDetailListener = {
            navigateToBankInformationFragment(it)
        }
    }

    private fun deleteBinNumberBySwipe() {
        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val bin = storedAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBinFromDatabase(bin.id)
            }
        })
        helper.attachToRecyclerView(binding.recyclerView)
    }

    private fun navigateToPassBinFragment() {
        binding.bottomNavigationStoredBinNumbersFragment.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pass_bin -> {
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToBankInformationFragment(bin: String) {
        findNavController().navigate(
            StoredBinNumbersFragmentDirections
                .actionStoredBinNumbersFragmentFragmentToInformationFragment(bin)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}