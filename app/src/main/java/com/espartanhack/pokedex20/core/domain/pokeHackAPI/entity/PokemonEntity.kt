package com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEntity(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "abilities")
    val abilities: Array<AbilityItem>,
    @SerialName(value = "cries")
    val sound: String,
    @SerialName(value = "height")
    val height: Int,
    @SerialName(value = "location_area_encounters")
    val locationEncounters: Array<Items>,
    @SerialName(value = "evolves_to")
    val evolvesTo: EvolvesTo? = null,
    @SerialName(value = "moves")
    val moves: Array<Move>,
    @SerialName(value = "species")
    val specie: Specie,
    @SerialName(value = "image")
    val image: String,
    @SerialName(value = "stats")
    val stats: Array<Stats>,
    @SerialName(value = "types")
    val types: Array<Types>,
    @SerialName(value = "weight")
    val weight: Int,
)

@Serializable
data class AbilityItem(
    @SerialName(value = "ability")
    val ability: Ability,
    @SerialName(value = "is_hidden")
    val isHidden: Boolean,
    @SerialName(value = "slot")
    val slot: Int,
)

@Serializable
data class Ability(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
)

@Serializable
data class Items(
    @SerialName(value = "items")
    val item: String
)

@Serializable
data class EvolvesTo(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "id")
    val id: Int,
)

@Serializable
data class Move(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
)

@Serializable
data class Specie(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
)

@Serializable
data class Stats(
    @SerialName(value = "item")
    val stat: StatItem,
)

@Serializable
data class StatItem(
    @SerialName(value = "base_stat")
    val baseStats: String,
    @SerialName(value = "effort")
    val effort: Integer,
    @SerialName(value = "stat")
    val stat: Stat,
)

@Serializable
data class Stat(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
)

@Serializable
data class Types(
    @SerialName(value = "slot")
    val slot: Int,
    @SerialName(value = "type")
    val type: Type,
)

@Serializable
data class Type(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
)