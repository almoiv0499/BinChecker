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
        private var INSTANCE: BinDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "bin_number.db"

        fun getInstance(application: Application): BinDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    BinDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}