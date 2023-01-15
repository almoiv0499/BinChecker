package ru.bin.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.bin.data.model.Bin

@Database(entities = [Bin::class], version = 1, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {

    abstract fun binDao(): BinDao

    companion object {

        @Volatile
        private var INSTANCE: BinDatabase? = null
        private const val DB_NAME = "bin_number.db"

        fun getDatabase(application: Application): BinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application,
                    BinDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}