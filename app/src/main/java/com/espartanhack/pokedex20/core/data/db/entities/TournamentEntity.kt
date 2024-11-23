package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(
    tableName = "tournament_table",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["winner_id"]
        )
    ]
)
data class TournamentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "time")
    val time: ZonedDateTime,
    @ColumnInfo(name = "can_register")
    val canRegister: Boolean,
    @ColumnInfo(name = "winner_id")
    val winnerId: String? = null,
)
