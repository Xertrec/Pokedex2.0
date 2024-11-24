package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity

@Entity(
    tableName = "captured_pokemons_cross_ref",
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
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "team_id")
    val teamId: String,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
)
