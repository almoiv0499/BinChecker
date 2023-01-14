package ru.bin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_numbers")
data class Bin(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val binNumber: String
)
