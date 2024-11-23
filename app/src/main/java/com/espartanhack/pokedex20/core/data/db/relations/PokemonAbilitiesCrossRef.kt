package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonAbilitiesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity

@Entity(
    tableName = "pokemon_abilities_cross_ref",
    primaryKeys = ["pokemon_id", "ability_name"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = PokemonAbilitiesEntity::class,
            parentColumns = ["abilityName"],
            childColumns = ["ability_name"]
        )
    ]
)
data class PokemonAbilitiesCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "ability_name")
    val abilityName: String
)
