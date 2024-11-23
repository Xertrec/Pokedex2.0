package com.espartanhack.pokedex20.core.domain.pokeHackAPI

import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.ZoneEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.header

//class UserApi(private val client: HttpClient) {
//    suspend fun getUserKtor(
//        userId: String
//    ): ZoneEntity = client.get("$END_POINT_GET_USER_KTOR$userId")
//
//    suspend fun saveUser(user: ZoneEntity) {
//        client.post<ZoneEntity>("$END_POINT_POST_USER_KTOR") {
//            body = user
//        }
//    }
//}