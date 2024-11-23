package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity

@Entity(
    tableName = "captured_pokemons_cross_ref",
    primaryKeys = ["team_id", "pokemon_id"],
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["team_id"]
        ),
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        )
    ]
)
data class CapturedPokemonsCrossRef(
    @ColumnInfo(name = "team_id")
    val teamId: String,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
)
