package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "requests_table",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["team_id"]
        )
    ]
)
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "team_id")
    val teamId: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: String,
)