package com.example.quotesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp.domain.model.Quote


@Database(entities = [Quote::class], version = 2)
abstract class LocalDatabase :RoomDatabase(){
    abstract fun quoteDao():QuoteDao
}

//@Database(entities = [Quote::class], version = 1)
//abstract class QuoteDatabase : RoomDatabase() {
//    abstract fun quoteDao(): QuoteDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: QuoteDatabase? = null
//
//        fun getDatabase(context: Context): QuoteDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    QuoteDatabase::class.java,
//                    "quote_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}