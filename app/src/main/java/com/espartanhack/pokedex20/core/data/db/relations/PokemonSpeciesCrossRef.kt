package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonSpeciesEntity

@Entity(
    tableName = "pokemon_species_cross_ref",
    primaryKeys = ["pokemon_id", "species_name"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = PokemonSpeciesEntity::class,
            parentColumns = ["species_name"],
            childColumns = ["species_name"]
        )
    ]
)
data class PokemonSpeciesCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "species_name")
    val speciesName: String
)
