package com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamEntityApi(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "pve_score")
    val pveScore: Int,
    @SerialName(value = "pvp_score")
    val pvpScore: Int,
    @SerialName(value = "pokedex_score")
    val pokedexScore: Int,
    @SerialName(value = "captured_pokemons")
    val capturedPokemon: Array<String>,
    @SerialName(value = "is_active")
    val isActive: Boolean,
)
