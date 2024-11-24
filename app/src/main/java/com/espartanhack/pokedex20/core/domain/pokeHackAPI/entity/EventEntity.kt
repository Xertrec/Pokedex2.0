package com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventEntity(
    @SerialName(value = "team_id")
    val teamId: String,
    @SerialName(value = "captured_pokemon_uuid")
    val catchedPokemonId: String? = null,
    @SerialName(value = "pokemon_uuid_list")
    val pokemonsEvent: Array<PokemonList>
)

@Serializable
data class PokemonList(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "pokemon_id")
    val pokemonId: Int
)
