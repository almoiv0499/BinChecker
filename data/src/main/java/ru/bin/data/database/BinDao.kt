package ru.bin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.bin.data.model.Bin

@Dao
interface BinDao {

    @Query("SELECT * FROM bin_numbers")
    fun getListStoredBinNumbers(): Flow<List<Bin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBinToDatabase(bin: Bin)

    @Query("DELETE FROM bin_numbers WHERE id=:binId")
    suspend fun deleteBinFromDatabase(binId: Long)
}