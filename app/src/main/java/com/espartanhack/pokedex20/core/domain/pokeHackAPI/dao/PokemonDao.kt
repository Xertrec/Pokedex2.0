package com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao

import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.PokemonEntityApi
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDao @Inject constructor(
    private val prefs: Prefs
) {

    suspend fun evolvePokemon(pokemonId: String) {
        val client = httpClient

        try {
            TODO("Add 3 pokemon caught uids")
            client.post("${APITokens.API_URL}/pokemons/$pokemonId/evolve") {
                contentType(ContentType.Application.Json)
                setBody("""
                    {
                        "pokemon_uuid_list": [],
                        "team_id": "${prefs.getString(Prefs.TEAM_ID).first()}"
                    }
                """.trimIndent())
            }
        } finally {
            client.close()
        }
    }

    suspend fun getPokemons(): List<PokemonEntityApi> {
        val client = httpClient

        return try {
            client.get("${APITokens.API_URL}/pokemons").body()
        } finally {
            client.close()
        }
    }

    suspend fun getPokemon(pokemonId: String): PokemonEntityApi {
        val client = httpClient

        return try {
            client.get("${APITokens.API_URL}/pokemons/${pokemonId}").body()
        } finally {
            client.close()
        }
    }
}