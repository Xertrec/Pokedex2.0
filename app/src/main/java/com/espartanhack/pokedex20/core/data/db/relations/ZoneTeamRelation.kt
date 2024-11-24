package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity

class ZoneTeamRelation(
    @Embedded val zone: ZonesEntity,
    @Relation(
        entity = ZoneTeamCrossRef::class,
        parentColumn = "id",
        entityColumn = "zone_id"
    )
    val zoneTeamRelation: ZoneTeamCrossRef,
)