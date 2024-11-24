package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonMovesEntity

@Entity(
    tableName = "pokemon_moves_cross_ref",
    primaryKeys = ["pokemon_id", "move_name"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = PokemonMovesEntity::class,
            parentColumns = ["move_name"],
            childColumns = ["move_name"]
        )
    ]
)
data class PokemonMovesCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "move_name")
    val moveName: String
)
