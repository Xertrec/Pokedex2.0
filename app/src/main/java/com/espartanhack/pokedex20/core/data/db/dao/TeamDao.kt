package com.espartanhack.pokedex20.core.data.db.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity

@Dao
interface TeamDao {

    @Upsert
    suspend fun upsertTeam(team: TeamEntity)

    @Upsert
    suspend fun upsertTeams(team: List<TeamEntity>)
}