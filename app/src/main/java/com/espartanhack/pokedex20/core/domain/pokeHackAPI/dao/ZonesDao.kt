package com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao

import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.ZoneEntity
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZonesDao @Inject constructor() {

    suspend fun getZone(zoneCode: String): ZoneEntity {
        val client = httpClient

        return client.get("${APITokens.API_URL}/zones/$zoneCode").body()
    }
}