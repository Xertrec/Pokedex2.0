package com.espartanhack.pokedex20.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity
import com.espartanhack.pokedex20.core.data.db.relations.ZoneTeamRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ZoneDao {

    @Query("SELECT * FROM zones_table WHERE id IN (SELECT zone_id FROM zone_team_cross_ref WHERE team_id = :teamId)")
    fun getZones(teamId: String): Flow<List<ZoneTeamRelation>>

    @Upsert
    suspend fun upsertZone(zone: ZonesEntity)

    @Upsert
    suspend fun upsertEvent(event: EventsEntity)
}