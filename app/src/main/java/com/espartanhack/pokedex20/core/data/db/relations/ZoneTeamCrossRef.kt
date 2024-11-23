package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity

@Entity(
    tableName = "zone_team_cross_ref",
    primaryKeys = ["zone_id", "team_id"],
    foreignKeys = [
        ForeignKey(
            entity = ZonesEntity::class,
            parentColumns = ["id"],
            childColumns = ["zone_id"]
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["team_id"]
        )
    ]
)
data class ZoneTeamCrossRef(
    @ColumnInfo(name = "zone_id")
    val zoneId: String,
    @ColumnInfo(name = "team_id")
    val teamId: String,
    @ColumnInfo(name = "cooldown_period")
    val cooldown: Float,
    @ColumnInfo(name = "last_request")
    val lastRequest: Long
)
