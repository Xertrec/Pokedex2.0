package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonSpeciesEntity

@Entity(
    tableName = "pokemon_species_cross_ref",
    primaryKeys = ["pokemonId", "speciesName"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"]
        ),
        ForeignKey(
            entity = PokemonSpeciesEntity::class,
            parentColumns = ["species_name"],
            childColumns = ["speciesName"]
        )
    ]
)
data class PokemonSpeciesCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "species_name")
    val speciesName: String
)
