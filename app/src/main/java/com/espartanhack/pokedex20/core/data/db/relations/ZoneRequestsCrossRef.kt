package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.RequestsEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity

@Entity(
    tableName = "zone_requests_relation",
    primaryKeys = ["zone_id", "request_id"],
    foreignKeys = [
        ForeignKey(
            entity = ZonesEntity::class,
            parentColumns = ["id"],
            childColumns = ["zone_id"]
        ),
        ForeignKey(
            entity = RequestsEntity::class,
            parentColumns = ["id"],
            childColumns = ["request_id"]
        )
    ]
)
data class ZoneRequestsCrossRef(
    @ColumnInfo(name = "zone_id")
    val zoneId: String,
    @ColumnInfo(name = "request_id")
    val requestId: Int
)