package ru.bin.domain.model

data class BinDomain(
    val binNumber: String,
    var id: Long = DEFAULT_VALUE,
) {
    companion object {
        private const val DEFAULT_VALUE = 0L
    }
}
