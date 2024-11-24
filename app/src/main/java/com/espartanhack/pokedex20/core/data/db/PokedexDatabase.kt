package com.espartanhack.pokedex20.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.espartanhack.pokedex20.core.data.db.dao.PokemonDao
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonAbilitiesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonMovesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonSpeciesEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonStatsEntity
import com.espartanhack.pokedex20.core.data.db.entities.PokemonTypeEntity
import com.espartanhack.pokedex20.core.data.db.entities.TeamEntity
import com.espartanhack.pokedex20.core.data.db.entities.TournamentCombatEntity
import com.espartanhack.pokedex20.core.data.db.entities.TournamentEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.CombatTeamCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonAbilitiesCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonEventCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonMovesCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.PokemonTypeCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.TournamentTeamCrossRef
import com.espartanhack.pokedex20.core.data.db.relations.ZoneTeamCrossRef

@Database(
    entities = [
        TeamEntity::class,
        ZonesEntity::class,
        ZoneTeamCrossRef::class,
        PokemonEntity::class,
        PokemonStatsEntity::class,
        PokemonTypeEntity::class,
        PokemonTypeCrossRef::class,
        PokemonSpeciesEntity::class,
        PokemonAbilitiesEntity::class,
        PokemonAbilitiesCrossRef::class,
        PokemonMovesEntity::class,
        PokemonMovesCrossRef::class,
        EventsEntity::class,
        PokemonEventCrossRef::class,
        CapturedPokemonsCrossRef::class,
        TournamentEntity::class,
        TournamentTeamCrossRef::class,
        TournamentCombatEntity::class,
        CombatTeamCrossRef::class
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = [
    ]
)
@TypeConverters(Converters::class)
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}