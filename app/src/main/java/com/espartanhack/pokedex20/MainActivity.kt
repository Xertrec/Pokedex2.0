package com.espartanhack.pokedex20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.espartanhack.pokedex20.core.data.db.entities.PokemonAbilitiesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonSpeciesEntity
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonAbilitiesCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonMovesCrossRef
import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao.PokemonDao
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao.TeamsDao
import com.espartanhack.pokedex20.core.presentation.navigation.NavGraph
import com.espartanhack.pokedex20.core.presentation.navigation.ScreenHome
import com.espartanhack.pokedex20.core.presentation.theme.Pokedex20Theme
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var prefs: Prefs

    @Inject lateinit var apiPokemonDao: Lazy<PokemonDao>
    @Inject lateinit var apiTeamsDao: Lazy<TeamsDao>

    @Inject lateinit var dbPokemonDao: Lazy<com.espartanhack.pokedex20.core.data.db.dao.PokemonDao>
    @Inject lateinit var dbTeamsDao: Lazy<com.espartanhack.pokedex20.core.data.db.dao.TeamDao>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Pokedex20Theme {
                NavGraph(
                    startScreen = ScreenHome::class,
                    navController = rememberNavController()
                )
            }

            LaunchedEffect(Unit) {
                prefs.saveString(Prefs.TEAM_ID, "28c083bc-f024-4f9b-8902-ddb715d89d3d")
                val initialSetup = prefs.getBool(Prefs.INITIAL_SETUP).first()
                if (!initialSetup) {
                    val pokemons = apiPokemonDao.get().getPokemons()
                    dbPokemonDao.get().upsertSpecies(
                        pokemons.map { it.specie }.map { specie ->
                            PokemonSpeciesEntity(
                                speciesName = specie.name,
                                speciesUrl = specie.url
                            )
                        }
                    )
                    dbPokemonDao.get().upsertPokemons(
                        pokemons.map { it.toDbEntity() }
                    )
                    dbPokemonDao.get().upsertAbilities(
                        pokemons.map { it.abilities }.flatMap { abilities ->
                            abilities.map {
                                PokemonAbilitiesEntity(
                                    abilityName = it.ability.name,
                                    abilityUrl = it.ability.url,
                                    isHidden = it.isHidden,
                                    slot = it.slot
                                )
                            }
                        }
                    )
                    dbPokemonDao.get().upsertAbilitiesCrossRef(
                        pokemons.map { pokemon ->
                            pokemon.abilities.map { ability ->
                                PokemonAbilitiesCrossRef(
                                    pokemonId = pokemon.id,
                                    abilityName = ability.ability.name
                                )
                            }
                        }.flatten()
                    )
                    dbPokemonDao.get().upsertMoves(
                        pokemons.map { it.moves }.flatMap { moves ->
                            moves.map {
                                com.espartanhack.pokedex20.core.data.db.entities.PokemonMovesEntity(
                                    moveName = it.name,
                                    moveUrl = it.url
                                )
                            }
                        }
                    )
                    dbPokemonDao.get().upsertPokemonMovesCrossRef(
                        pokemons.map { pokemon ->
                            pokemon.moves.map { move ->
                                PokemonMovesCrossRef(
                                    pokemonId = pokemon.id,
                                    moveName = move.name
                                )
                            }
                        }.flatten()
                    )

                    val team = apiTeamsDao.get().getTeam()
                    dbTeamsDao.get().upsertTeam(
                        TeamEntity(
                            id = team.id,
                            name = team.name,
                            pveScore = team.pveScore,
                            pvpScore = team.pvpScore,
                            pokedexScore = team.pokedexScore,
                            isActive = team.isActive
                        )
                    )
                    dbPokemonDao.get().upsertCatchedPokemons(
                        team.capturedPokemon.map {
                            CapturedPokemonsCrossRef(
                                id = it.id,
                                teamId = team.id,
                                pokemonId = it.pokemonId
                            )
                        }
                    )
//                    prefs.saveBool(Prefs.INITIAL_SETUP, true)
                }
            }
        }
    }
}