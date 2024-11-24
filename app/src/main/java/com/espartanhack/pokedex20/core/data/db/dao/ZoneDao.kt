package com.espartanhack.pokedex20.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity
import com.espartanhack.pokedex20.core.data.db.relations.ZoneTeamCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.ZoneTeamRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ZoneDao {

    @Query("SELECT * FROM zones_table WHERE id")
    fun getZones(): Flow<List<ZonesEntity>>

    @Upsert
    suspend fun upsertZone(zone: ZonesEntity)

    @Upsert
    suspend fun upsertZoneTeamCrossRef(zoneTeamCrossRef: ZoneTeamCrossRef)

    @Upsert
    suspend fun upsertZoneTeamCrossRef(zoneTeamCrossRef: List<ZoneTeamCrossRef>)

    @Upsert
    suspend fun upsertEvent(event: EventsEntity)
}