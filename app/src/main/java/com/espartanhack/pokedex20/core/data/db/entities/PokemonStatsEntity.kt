package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "pokemon_stats_table",
    primaryKeys = ["pokemon_id", "stat_name"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        )
    ]
)
data class PokemonStatsEntity(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "base_stat")
    val baseStat: Int,
    @ColumnInfo(name = "effort")
    val effort: Int,
    @ColumnInfo(name = "stat_name")
    val statName: String,
    @ColumnInfo(name = "stat_url")
    val statUrl: String
)
