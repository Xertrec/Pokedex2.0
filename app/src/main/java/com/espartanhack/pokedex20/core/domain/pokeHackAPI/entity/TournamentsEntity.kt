package com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity

import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TournamentsEntity(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "time")
    val time: String? = null,
    @SerialName(value = "can_register")
    val canRegister: Boolean,
    @SerialName(value = "teams")
    val teams: Array<String> = emptyArray(),
    @SerialName(value = "winner")
    val winner: String,
    @SerialName(value = "teams_position")
    val teamsPosition: Array<String>,
)

@Serializable
private data class TeamEntity(
    @SerialName(value = "team_id")
    val teamId: String,
    @SerialName(value = "pokemon_uuid_list")
    val pokemonUuidList: Array<String>,
)