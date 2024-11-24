package com.espartanhack.pokedex20.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.espartanhack.pokedex20.core.data.db.PokedexDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POKEDEX_DATABASE_NAME = "pokedex_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, PokedexDatabase::class.java, POKEDEX_DATABASE_NAME)
//            .fallbackToDestructiveMigration()
//            .enableMultiInstanceInvalidation()
            .build()

    @Singleton
    @Provides
    fun providePokemonDao(chatsDatabase: PokedexDatabase) = chatsDatabase.pokemonDao()
}