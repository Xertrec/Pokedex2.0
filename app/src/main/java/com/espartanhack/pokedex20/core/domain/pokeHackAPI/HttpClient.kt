package com.espartanhack.pokedex20.core.domain.pokeHackAPI

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(CIO) {
    expectSuccess = true

    install(ContentNegotiation) {
        json() // Example: Register JSON content transformation
        // Add more transformations as needed for other content types
    }
}