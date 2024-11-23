package com.espartanhack.pokedex20.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class TeamEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "pve_score")
    val pveScore: Int,
    @ColumnInfo(name = "pvp_score")
    val pvpScore: Int,
    @ColumnInfo(name = "pokedex_score")
    val pokedexScore: Int,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
)
