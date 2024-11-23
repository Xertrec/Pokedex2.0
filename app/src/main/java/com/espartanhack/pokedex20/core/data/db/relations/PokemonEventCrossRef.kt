package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity

@Entity(
    tableName = "pokemon_event_cross_ref",
    primaryKeys = ["pokemon_id", "event_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = EventsEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_id"]
        )
    ]
)
data class PokemonEventCrossRef(
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "event_id")
    val eventId: String
)
