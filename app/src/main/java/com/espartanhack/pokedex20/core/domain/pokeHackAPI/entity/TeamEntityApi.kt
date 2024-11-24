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
    val pveScore: Int = 0,
    @SerialName(value = "pvp_score")
    val pvpScore: Int = 0,
    @SerialName(value = "pokedex_score")
    val pokedexScore: Int = 0,
    @SerialName(value = "captured_pokemons")
    val capturedPokemon: Array<PokemonCatched> = emptyArray(),
    @SerialName(value = "is_active")
    val isActive: Boolean = true
)

@Serializable
data class PokemonCatched(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "pokemon_id")
    val pokemonId: Int,
)