package com.example.besinkitabi.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.besinkitabi.model.Besin

@Database (entities = [Besin::class], version = 1)

abstract class BesinDB : RoomDatabase() {
    abstract fun besinDao() : BesinDao

    companion object {
        @Volatile
        private var instance : BesinDB? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }
        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BesinDB::class.java,
            "besindatabase"
        ).build()
    }
}
