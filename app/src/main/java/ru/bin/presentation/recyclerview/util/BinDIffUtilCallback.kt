package ru.bin.presentation.recyclerview.util

import androidx.recyclerview.widget.DiffUtil
import ru.bin.presentation.model.BinView

class BinDIffUtilCallback : DiffUtil.ItemCallback<BinView>() {

    override fun areItemsTheSame(oldItem: BinView, newItem: BinView): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BinView, newItem: BinView): Boolean =
        oldItem.binNumber == newItem.binNumber

}