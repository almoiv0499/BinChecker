package ru.bin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bin.app.MyApplication
import ru.bin.presentation.R
import ru.bin.presentation.databinding.FragmentStoredBinNumbersBinding
import ru.bin.presentation.fragment.basefragment.BaseFragment
import ru.bin.presentation.fragment.viewmodel.StoredBinNumbersViewModel
import ru.bin.presentation.fragment.viewmodelfactory.BinViewModelFactory
import ru.bin.presentation.recyclerview.adapter.BinAdapter
import javax.inject.Inject

class StoredBinNumbersFragment : BaseFragment<StoredBinNumbersViewModel>() {

    private var _binding: FragmentStoredBinNumbersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
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
        listenerButtonNavigateToPassBinFragment()
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

    private fun listenerButtonNavigateToPassBinFragment() {
        binding.bottomNavigationStoredBinNumbersFragment.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pass_bin -> {
                    navigateToPassBinFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToPassBinFragment() {
        viewModel.navigateBackToPassBinFragment()
    }

    private fun navigateToBankInformationFragment(binNumber: String) {
        viewModel.navigateToBankInformationFragment(binNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}