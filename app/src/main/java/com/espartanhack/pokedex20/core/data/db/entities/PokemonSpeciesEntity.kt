package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_species_table")
data class PokemonSpeciesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "species_name")
    val speciesName: String,
    @ColumnInfo(name = "species_url")
    val speciesUrl: String,
)
