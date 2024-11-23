package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_type_entity")
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "type_name")
    val typeName: String,
    @ColumnInfo(name = "type_url")
    val typeUrl: String
)
