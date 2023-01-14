package ru.bin.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.bin.presentation.databinding.RecyclerBinNumberBinding
import ru.bin.presentation.model.BinView
import ru.bin.presentation.util.BinDIffUtilCallback
import ru.bin.presentation.viewholder.BinViewHolder

class BinAdapter : ListAdapter<BinView, BinViewHolder>(BinDIffUtilCallback()) {

    var toBinDetailListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val binding = RecyclerBinNumberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        val bin = currentList[position]
        holder.bind(bin)
        holder.itemView.setOnClickListener {
            toBinDetailListener?.invoke(bin.binNumber)
        }
    }

}