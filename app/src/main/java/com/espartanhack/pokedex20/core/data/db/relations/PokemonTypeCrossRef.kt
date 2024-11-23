package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonTypeEntity

@Entity(
    tableName = "pokemon_type_cross_ref",
    primaryKeys = ["pokemon_id", "type_name"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = PokemonTypeEntity::class,
            parentColumns = ["type_name"],
            childColumns = ["type_name"]
        )
    ]
)
data class PokemonTypeCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "type_name")
    val typeName: String,
    @ColumnInfo(name = "slot")
    val slots: Int
)
