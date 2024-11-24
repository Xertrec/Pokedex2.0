package com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao

import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.EventEntity
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.TournamentsEntity
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
class TournamentsDao @Inject constructor(
    private val prefs: Prefs
) {

    suspend fun getTournaments(): List<TournamentsEntity> {
        val client = httpClient

        return try {
            client.get("${APITokens.API_URL}/tournaments").body()
        } finally {
            client.close()
        }
    }

    suspend fun getTournament(tournamentId: String): TournamentsEntity {
        val client = httpClient

        return try {
            client.get("${APITokens.API_URL}/tournaments/${tournamentId}").body()
        } finally {
            client.close()
        }
    }

    suspend fun putTournament(tournamentId: String): TournamentsEntity {
        val client = httpClient

        return try {
            TODO("Add pokemon caught")
            client.post("${APITokens.API_URL}/tournaments/$tournamentId") {
                contentType(ContentType.Application.Json)
                setBody("""
                    {
                        "team_id": "${prefs.getString(Prefs.TEAM_ID).first()}",
                        "pokemon_uuid_list": []
                    }
                """.trimIndent())
            }.body<TournamentsEntity>()
        } finally {
            client.close()
        }
    }
}