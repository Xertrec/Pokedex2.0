package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef

@Entity(
    tableName = "events_table",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["team_id"]
        ),
        ForeignKey(
            entity = CapturedPokemonsCrossRef::class,
            parentColumns = ["id"],
            childColumns = ["captured_pokemon_id"]
        )
    ]
)
data class EventsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "team_id")
    val teamId: String,
    @ColumnInfo(name = "captured_pokemon_id")
    val capturedPokemonId: String,
)
