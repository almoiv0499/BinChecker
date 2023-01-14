package ru.bin.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.bin.presentation.databinding.RecyclerBinNumberBinding
import ru.bin.presentation.model.BinView

class BinViewHolder(
    private val binding: RecyclerBinNumberBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bin: BinView) {
        binding.storedBinNumber.text = bin.binNumber
    }
}