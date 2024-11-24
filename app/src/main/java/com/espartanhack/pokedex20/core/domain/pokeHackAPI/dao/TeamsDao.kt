package com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao

import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.TeamEntityApi
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsDao @Inject constructor(
    private val prefs: Prefs
) {

    suspend fun getTeams(): List<TeamEntityApi> {
        val client = httpClient

        return client.get("${APITokens.API_URL}/teams?token=query").body()
    }

    suspend fun getTeam(): TeamEntityApi {
        val client = httpClient

        return client.get("${APITokens.API_URL}/teams/${prefs.getString(Prefs.TEAM_ID).first()}").body()
    }
}