package com.espartanhack.pokedex20.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.espartanhack.pokedex20.core.data.db.entities.PokemonAbilitiesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonMovesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonSpeciesEntity
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonAbilitiesCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonMovesCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Transaction
    @Query("SELECT * FROM pokemon_table WHERE id in (SELECT pokemon_id FROM captured_pokemons_cross_ref WHERE team_id = :teamId)")
    fun getCatchedPokemons(teamId: String): Flow<List<PokemonEntity>>

    @Upsert
    suspend fun upsertCatchedPokemon(catchedPokemon: CapturedPokemonsCrossRef)

    @Upsert
    suspend fun upsertCatchedPokemons(catchedPokemon: List<CapturedPokemonsCrossRef>)

    @Upsert
    suspend fun upsertSpecies(species: List<PokemonSpeciesEntity>)

    @Upsert
    suspend fun upsertPokemons(pokemons: List<PokemonEntity>)

    @Upsert
    suspend fun upsertAbilities(abilities: List<PokemonAbilitiesEntity>)

    @Upsert
    suspend fun upsertAbilitiesCrossRef(abilities: List<PokemonAbilitiesCrossRef>)

    @Upsert
    suspend fun upsertMoves(moves: List<PokemonMovesEntity>)

    @Upsert
    suspend fun upsertPokemonMovesCrossRef(moves: List<PokemonMovesCrossRef>)



}