package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_abilities_table")
data class PokemonAbilitiesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ability_name")
    val abilityName: String,
    @ColumnInfo(name = "ability_url")
    val abilityUrl: String,
    @ColumnInfo(name = "is_hidden")
    val isHidden: Boolean,
    @ColumnInfo(name = "slot")
    val slot: Int
)
