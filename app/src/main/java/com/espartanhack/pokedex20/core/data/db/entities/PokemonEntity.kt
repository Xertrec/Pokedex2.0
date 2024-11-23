package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

/**
 * @param id The id of the Pokémon in the Pokédex.
 */
@Entity(
    tableName = "pokemon_table",
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["evolves_to"]
        )
    ]
)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "cries")
    val cries: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "evolves_to")
    val evolvesTo: Int? = null,
)
