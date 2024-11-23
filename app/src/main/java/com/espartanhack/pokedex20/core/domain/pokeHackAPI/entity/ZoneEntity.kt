package com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZoneEntity(
    @SerialName(value = "id")
    val id: String? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("cooldown_period")
    val cooldown: Float,
    @SerialName("last_requests_by_team")
    val lastRequestsByTeam: Array<RequestsByTeam> = emptyArray(),
    @SerialName("pokemon_prob_list")
    val pokemonProbList: Array<ProbItem> = emptyArray()
)

@Serializable
data class RequestsByTeam(
    @SerialName("name")
    val name: String?,
    @SerialName("timestamp")
    val timestamp: String
)

@Serializable
data class ProbItem(
    @SerialName("pokemon_id")
    val id: String,
    @SerialName("probability")
    val probability: Int,
    @SerialName("fail_prob")
    val failProb: Int,
)