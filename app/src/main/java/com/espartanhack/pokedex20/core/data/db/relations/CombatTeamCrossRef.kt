package com.espartanhack.pokedex20.core.data.db.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity
import com.espartanhack.pokedex20.core.data.db.entities.TournamentCombatEntity

@Entity(
    tableName = "combat_team_cross_ref",
    primaryKeys = ["combatId", "teamId"],
    foreignKeys = [
        ForeignKey(
            entity = TournamentCombatEntity::class,
            parentColumns = ["id"],
            childColumns = ["combatId"]
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["teamId"]
        )
    ]
)
data class CombatTeamCrossRef(
    @ColumnInfo(name = "combat_id")
    val combatId: Int,
    @ColumnInfo(name = "team_id")
    val teamId: String
)
