package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_moves_table")
data class PokemonMovesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "move_name")
    val moveName: String,
    @ColumnInfo(name = "move_url")
    val moveUrl: String
)
