package ru.bin.presentation.model

data class BinView(
    val binNumber: String,
    var id: Long = DEFAULT_VALUE
) {
    companion object {
        private const val DEFAULT_VALUE = 0L
    }
}
