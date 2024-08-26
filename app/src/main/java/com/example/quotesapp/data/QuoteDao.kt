package com.example.quotesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend  fun insertQuote(quote:Quote)

    @Delete
     suspend fun deleteQuote(quote: Quote)

    @Query("SELECT * FROM Quote ORDER BY id ASC")
    fun getAllQuotes(): Flow<List<Quote>>

}