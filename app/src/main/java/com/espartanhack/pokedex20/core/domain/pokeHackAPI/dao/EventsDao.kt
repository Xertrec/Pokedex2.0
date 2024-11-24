package com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao

import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.EventEntity
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.httpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsDao @Inject constructor(
    private val prefs: Prefs
) {

    suspend fun postEvent(zoneId: String): EventEntity {
        val client = httpClient

        return client.post("${APITokens.API_URL}/events/$zoneId") {
            contentType(ContentType.Application.Json)
            setBody("""
                    {
                        "team_id": "${prefs.getString(Prefs.TEAM_ID).first()}"
                    }
                """.trimIndent())
        }.body<EventEntity>()
    }
}