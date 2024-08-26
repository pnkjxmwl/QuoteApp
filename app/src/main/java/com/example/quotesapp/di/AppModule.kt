package com.example.quotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.quotesapp.data.LocalDatabase
import com.example.quotesapp.data.QuotesApi
import com.example.quotesapp.data.repository.LocalDataRepositoryImpl
import com.example.quotesapp.data.repository.RemoteDataRepositoryImpl
import com.example.quotesapp.domain.repository.LocalDataRepository
import com.example.quotesapp.domain.repository.RemoteDataRepository
import com.example.quotesapp.domain.use_cases.DeleteSavedQuoteUseCase
import com.example.quotesapp.domain.use_cases.GetQuoteUseCase
import com.example.quotesapp.domain.use_cases.GetSavedQuoteUseCase
import com.example.quotesapp.domain.use_cases.InsertSavedQuoteUseCase
import com.example.quotesapp.domain.use_cases.UseCases
import com.example.quotesapp.util.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideQuotesApi(moshi: Moshi) :QuotesApi{
        return  Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(QuotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: QuotesApi):RemoteDataRepository{
        return RemoteDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RemoteDataRepository,
                        Localrepository: LocalDataRepository
                        ):UseCases
    {
        return UseCases(
            getQuoteUseCase = GetQuoteUseCase(repository),
            getSavedQuoteUseCase = GetSavedQuoteUseCase(Localrepository),
            insertSavedQuoteUseCase = InsertSavedQuoteUseCase(Localrepository),
            deleteSavedQuoteUseCase = DeleteSavedQuoteUseCase(Localrepository)
        )
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) : LocalDatabase=
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "local_database"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideLocalDataRepository(db:LocalDatabase) : LocalDataRepository{
        return LocalDataRepositoryImpl(dao=db.quoteDao())
    }
}